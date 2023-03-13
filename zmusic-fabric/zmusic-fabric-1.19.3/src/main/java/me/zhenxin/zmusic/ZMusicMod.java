package me.zhenxin.zmusic;

import me.zhenxin.zmusic.event.EventHandler;
import me.zhenxin.zmusic.manager.SoundManagerImpl;
import net.fabricmc.api.ModInitializer;

/**
 * Mod 主入口
 *
 * @author 真心
 * @email qgzhenxin@qq.com
 * @since 2023/1/28 13:01
 */
@SuppressWarnings("AlibabaClassNamingShouldBeCamel")
public class ZMusicMod implements ModInitializer {

    @Override
    public void onInitialize() {
        ZMusic.setSoundManager(new SoundManagerImpl());
        EventHandler.registry();
        ZMusic.onEnable();
    }
}