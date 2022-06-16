package me.zhenxin.zmusic.player;

import com.goxr3plus.streamplayer.stream.StreamPlayer;
import com.goxr3plus.streamplayer.stream.StreamPlayerEvent;
import com.goxr3plus.streamplayer.stream.StreamPlayerListener;

import java.util.Map;

/**
 * @author 真心
 * @since 2022/6/16 17:38
 */
public class MusicPlayer extends StreamPlayer implements StreamPlayerListener {
    private final SoundVolume volume;

    public MusicPlayer(SoundVolume volume) {
        addStreamPlayerListener(this);
        this.volume = volume;
    }

    @Override
    public void opened(Object dataSource, Map<String, Object> properties) {

    }

    @Override
    public void progress(int nEncodedBytes, long microsecondPosition, byte[] pcmData, Map<String, Object> properties) {
        setGain(this.volume.getVolume());
    }

    @Override
    public void statusUpdated(StreamPlayerEvent event) {

    }
}
