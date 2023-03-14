package me.zhenxin.zmusic.player;

import com.goxr3plus.streamplayer.stream.StreamPlayer;
import com.goxr3plus.streamplayer.stream.StreamPlayerEvent;
import com.goxr3plus.streamplayer.stream.StreamPlayerListener;
import lombok.extern.log4j.Log4j2;
import me.zhenxin.zmusic.ZMusic;

import java.util.Map;


/**
 * @author 真心
 * @since 2022/6/16 17:38
 */
@Log4j2
public class MusicPlayer extends StreamPlayer implements StreamPlayerListener {

    public MusicPlayer() {
        addStreamPlayerListener(this);
    }

    @Override
    public void opened(Object dataSource, Map<String, Object> properties) {

    }

    @Override
    public void progress(int nEncodedBytes, long microsecondPosition, byte[] pcmData, Map<String, Object> properties) {
        setGain(ZMusic.getSoundManager().volume());
    }

    @Override
    public void statusUpdated(StreamPlayerEvent event) {
        log.info("Player Status Updated: {}", event.getPlayerStatus());
    }
}