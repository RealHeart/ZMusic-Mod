package me.zhenxin.zmusic.player;

import lombok.extern.log4j.Log4j2;
import me.zhenxin.zmusic.ZMusic;
import me.zhenxin.zmusic.player.decoder.BuffPack;
import me.zhenxin.zmusic.player.decoder.IDecoder;
import me.zhenxin.zmusic.player.decoder.flac.FlacDecoder;
import me.zhenxin.zmusic.player.decoder.mp3.Mp3Decoder;
import me.zhenxin.zmusic.player.decoder.ogg.OggDecoder;
import org.lwjgl.BufferUtils;
import org.lwjgl.openal.AL10;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.Queue;
import java.util.concurrent.*;

@SuppressWarnings({"AlibabaClassMustHaveAuthor", "AlibabaClassNamingShouldBeCamel", "AlibabaAvoidManuallyCreateThread", "AlibabaUndefineMagicConstant", "HttpUrlsUsage", "AlibabaLowerCamelCaseVariableNaming", "NullableProblems"})
@Log4j2
public class MusicPlayer extends InputStream {

    private HttpURLConnection connection;
    private String url;
    private InputStream content;

    private boolean isClose = false;
    private boolean reload = false;
    private IDecoder decoder;
    private final Queue<String> urls = new ConcurrentLinkedQueue<>();
    private int time = 0;
    private long local = 0;
    private final Semaphore semaphore = new Semaphore(0);
    private final Semaphore semaphore1 = new Semaphore(0);
    private final Queue<ByteBuffer> queue = new ConcurrentLinkedQueue<>();
    private boolean isPlay = false;
    private boolean wait = false;
    private int index;
    private int frequency;
    private int channels;

    public MusicPlayer() {
        try {
            new Thread(this::run, "zmusic-player-thread").start();
            ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
            service.scheduleAtFixedRate(this::run1, 0, 10, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void run1() {
        if (isPlay) {
            time += 10;
        }
    }

    public boolean isPlay() {
        return isPlay;
    }

    public static URL get(URL url) {
        if (url.toString().contains("https://music.163.com/song/media/outer/url?id=")
                || url.toString().contains("http://music.163.com/song/media/outer/url?id=")) {
            try {
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setConnectTimeout(4 * 1000);
                connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/84.0.4147.105 Safari/537.36 Edg/84.0.522.52");
                connection.setRequestProperty("Host", "music.163.com");
                connection.connect();
                if (connection.getResponseCode() == 302) {
                    return new URL(connection.getHeaderField("Location"));
                }
                return connection.getURL();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return url;
    }

    public void set(String time) {
        try {
            int time1 = Integer.parseInt(time);
            set(time1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void set(int time) {
        closePlayer();
        this.time = time;
        urls.add(url);
        semaphore.release();
    }

    public void connect() throws IOException {
        streamClose();
        URL urlObject = new URL(url);
        connection = (HttpURLConnection) urlObject.openConnection();
        connection.setRequestProperty("Range", "bytes=" + local + "-");
        connection.setRequestProperty("User-Agent", "ZMusic Mod/" + ZMusic.getVersion());
        connection.connect();

        int responseCode = connection.getResponseCode();
        if (responseCode >= 200 && responseCode < 300) {
            content = new BufferedInputStream(connection.getInputStream());
        } else {
            throw new IOException("Failed to connect, response code: " + responseCode);
        }
    }

    @SuppressWarnings("AlibabaMethodTooLong")
    private void run() {
        while (true) {
            try {
                semaphore.acquire();
                url = urls.poll();
                if (url == null || url.isEmpty()) {
                    continue;
                }
                urls.clear();
                URL nowURL = new URL(url);
                nowURL = get(nowURL);
                if (nowURL == null) {
                    continue;
                }
                try {
                    local = 0;
                    connect();
                } catch (Exception e) {
                    e.printStackTrace();
                    log.warn("Failed to get the music!");
                    continue;
                }

                decoder = new FlacDecoder(this);
                if (!decoder.set()) {
                    local = 0;
                    connect();
                    decoder = new OggDecoder(this);
                    if (!decoder.set()) {
                        local = 0;
                        connect();
                        decoder = new Mp3Decoder(this);
                        if (!decoder.set()) {
                            log.warn("An unsupported file format!");
                            continue;
                        }
                    }
                }

                isPlay = true;
                index = AL10.alGenSources();
                int m_numqueued = AL10.alGetSourcei(index, AL10.AL_BUFFERS_QUEUED);
                while (m_numqueued > 0) {
                    int temp = AL10.alSourceUnqueueBuffers(index);
                    AL10.alDeleteBuffers(temp);
                    m_numqueued--;
                }
                frequency = decoder.getOutputFrequency();
                channels = decoder.getOutputChannels();
                if (channels != 1 && channels != 2) {
                    continue;
                }
                if (time != 0) {
                    decoder.set(time);
                }
                queue.clear();
                reload = false;
                isClose = false;
                while (true) {
                    try {
                        if (isClose) {
                            break;
                        }
                        BuffPack output = decoder.decodeFrame();
                        if (output == null) {
                            break;
                        }
                        ByteBuffer byteBuffer = BufferUtils.createByteBuffer(
                                output.len).put(output.buff, 0, output.len);
                        ((Buffer) byteBuffer).flip();
                        queue.add(byteBuffer);
                    } catch (Exception e) {
                        if (!isClose) {
                            e.printStackTrace();
                        }
                        break;
                    }
                }
                streamClose();
                decodeClose();
                while (!isClose && AL10.alGetSourcei(index,
                        AL10.AL_SOURCE_STATE) == AL10.AL_PLAYING) {
                    AL10.alSourcef(index, AL10.AL_GAIN, ZMusic.getSoundManager().volume());
                    //noinspection BusyWait
                    Thread.sleep(100);
                }
                if (!reload) {
                    wait = true;
                    if (semaphore1.tryAcquire(500, TimeUnit.MILLISECONDS)) {
                        if (reload) {
                            urls.add(url);
                            semaphore.release();
                            continue;
                        }
                    }
                    isPlay = false;
                    AL10.alSourceStop(index);
                    m_numqueued = AL10.alGetSourcei(index, AL10.AL_BUFFERS_QUEUED);
                    while (m_numqueued > 0) {
                        int temp = AL10.alSourceUnqueueBuffers(index);
                        AL10.alDeleteBuffers(temp);
                        m_numqueued--;
                    }
                    AL10.alDeleteSources(index);
                } else {
                    urls.add(url);
                    semaphore.release();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void tick() {
        if (wait) {
            wait = false;
            semaphore1.release();
        }
        if (isClose) {
            queue.clear();
            return;
        }
        while (!queue.isEmpty()) {
            ByteBuffer byteBuffer = queue.poll();
            if (byteBuffer == null) {
                continue;
            }
            if (isClose) {
                return;
            }
            IntBuffer intBuffer = BufferUtils.createIntBuffer(1);
            AL10.alGenBuffers(intBuffer);

            AL10.alBufferData(intBuffer.get(0), channels == 1
                    ? AL10.AL_FORMAT_MONO16 : AL10.AL_FORMAT_STEREO16, byteBuffer, frequency);
            AL10.alSourcef(index, AL10.AL_GAIN, ZMusic.getSoundManager().volume());

            AL10.alSourceQueueBuffers(index, intBuffer);
            if (AL10.alGetSourcei(index,
                    AL10.AL_SOURCE_STATE) != AL10.AL_PLAYING) {
                AL10.alSourcePlay(index);
            }
        }
    }

    public void closePlayer() {
        isClose = true;
    }

    public void setMusic(String url) {
        time = 0;
        closePlayer();
        urls.add(url);
        semaphore.release();
    }

    private void streamClose() throws IOException {
        if (content != null) {
            content.close();
            content = null;
        }
        if (connection != null) {
            connection.disconnect();
            connection = null;
        }
    }

    private void decodeClose() throws Exception {
        if (decoder != null) {
            decoder.close();
            decoder = null;
        }
    }


    @Override
    public int read() throws IOException {
        return content.read();
    }

    @Override
    public int read(byte[] buf) throws IOException {
        return content.read(buf);
    }

    @Override
    public synchronized int read(byte[] buf, int off, int len)
            throws IOException {
        try {
            int temp = content.read(buf, off, len);
            local += temp;
            return temp;
        } catch (IOException ex) {
            connect();
            return read(buf, off, len);
        }
    }

    @Override
    public synchronized int available() throws IOException {
        return content.available();
    }

    @Override
    public void close() throws IOException {
        streamClose();
    }

    public void setLocal(long local) throws IOException {
        streamClose();
        this.local = local;
        connect();
    }

    public void setReload() {
        if (isPlay) {
            reload = true;
            isClose = true;
        }
    }
}
