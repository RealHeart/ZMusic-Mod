package me.zhenxin.zmusic;

import me.zhenxin.zmusic.player.MusicPlayer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ZMusic
 *
 * @author 真心
 * @since 2022/6/16 17:42
 */
public class ZMusic {
    public static MusicPlayer player;

    public static Logger getLogger() {
        return LoggerFactory.getLogger(ZMusic.class);
    }
}
