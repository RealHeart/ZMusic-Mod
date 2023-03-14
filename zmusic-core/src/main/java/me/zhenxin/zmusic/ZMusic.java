package me.zhenxin.zmusic;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import me.zhenxin.zmusic.manager.SoundManager;
import me.zhenxin.zmusic.player.MusicPlayer;


/**
 * ZMusic 主入口
 *
 * @author 真心
 * @email qgzhenxin@qq.com
 * @since 2023/1/28 13:08
 */
@SuppressWarnings("AlibabaClassNamingShouldBeCamel")
@Log4j2
public class ZMusic {
    @Getter
    private static final MusicPlayer player = new MusicPlayer();
    @Getter
    @Setter
    private static SoundManager soundManager = null;

    public static void onEnable() {
        log.info("Welcome use ZMusic Mod!");
        log.info("Homepage: https://m.zplu.cc");
        log.info("Github: https://github.com/RealHeart/ZMusic-Mod");
        log.info("Discord: https://discord.gg/twQgJNufYn");
        log.info("QQ Group: 1032722724");
    }
}