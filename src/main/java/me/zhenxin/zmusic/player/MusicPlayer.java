package me.zhenxin.zmusic.player;

import javax.sound.sampled.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * 音乐播放器
 *
 * @author 真心
 * @since 2022/2/20 11:32
 */
public class MusicPlayer {

    private static DataLine.Info info = null;
    private static AudioFormat format = null;
    private static SourceDataLine line = null;
    private static AudioInputStream audio = null;

    public void play(String url) throws Exception {
        load(new URL(url));
        try {
            line = (SourceDataLine) AudioSystem.getLine(info);
            line.open(format);
            line.start();
            byte[] buffer = new byte[1024];
            int len = -1;
            while ((len = audio.read(buffer)) > 0) {
                line.write(buffer, 0, len);
            }
            line.drain();
            line.stop();
            line.close();
            audio.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        if (line != null) {
            line.stop();
            line.close();
            try {
                audio.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void load(URL url) throws Exception {
        load(AudioSystem.getAudioInputStream(url));
    }

    public void load(InputStream stream) throws Exception {
        loadAudio(AudioSystem.getAudioInputStream(stream));
    }

    public void loadAudio(AudioInputStream stream) {
        try {
            format = stream.getFormat();
            format = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, format.getSampleRate(), 16, format.getChannels(),
                    format.getChannels() * 2, format.getSampleRate(), false);
            audio = AudioSystem.getAudioInputStream(format, stream);
            info = new DataLine.Info(SourceDataLine.class, format, AudioSystem.NOT_SPECIFIED);
            line = (SourceDataLine) AudioSystem.getLine(info);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
