package me.zhenxin.zmusic;

import me.zhenxin.zmusic.event.EventHandler;
import me.zhenxin.zmusic.player.MusicPlayer;
import me.zhenxin.zmusic.player.impl.SoundVolumeFabric;
import me.zhenxin.zmusic.utils.impl.LoggerFabric;
import net.fabricmc.api.ModInitializer;

/**
 * ZMusic Mod 主入口 Fabric
 *
 * @author 真心
 * @since 2022/6/15 19:47
 */
public class FabricMod implements ModInitializer {

    @Override
    public void onInitialize() {
        ZMusic.player = new MusicPlayer(new SoundVolumeFabric());
        ZMusic.setLogger(new LoggerFabric());
        EventHandler.registry();
        ZMusic.getLogger().info("Welcome use ZMusic Mod!");
        ZMusic.getLogger().info("Homepage: https://m.zplu.cc");
        ZMusic.getLogger().info("Discord: https://discord.gg/twQgJNufYn");
    }
}