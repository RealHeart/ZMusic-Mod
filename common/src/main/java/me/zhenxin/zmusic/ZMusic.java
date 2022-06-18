package me.zhenxin.zmusic;

import me.zhenxin.zmusic.player.MusicPlayer;
import me.zhenxin.zmusic.utils.Logger;

/**
 * ZMusic
 *
 * @author 真心
 * @since 2022/6/16 17:42
 */
public class ZMusic {
    public static MusicPlayer player;

    private static Logger logger;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        ZMusic.logger = logger;
    }
}
