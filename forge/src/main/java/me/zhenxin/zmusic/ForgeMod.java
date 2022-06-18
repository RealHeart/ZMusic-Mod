package me.zhenxin.zmusic;

import me.zhenxin.zmusic.channel.ChannelRegistryActive;
import me.zhenxin.zmusic.channel.ChannelRegistryLegacy;
import me.zhenxin.zmusic.player.MusicPlayer;
import me.zhenxin.zmusic.player.impl.SoundVolumeForge;
import me.zhenxin.zmusic.utils.impl.LoggerForge;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

/**
 * ZMusic Mod 主入口 Forge
 *
 * @author 真心
 * @since 2022/6/15 22:22
 */
@Mod("zmusic")
public class ForgeMod {


    public ForgeMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::setup);
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(FMLClientSetupEvent event) {
        ZMusic.player = new MusicPlayer(new SoundVolumeForge());
        ZMusic.setLogger(new LoggerForge());

        try {
            new ChannelRegistryActive();
        } catch (Throwable t) {
            new ChannelRegistryLegacy();
        }

        ZMusic.getLogger().info("Welcome use ZMusic Mod!");
        ZMusic.getLogger().info("Homepage: https://m.zplu.cc");
        ZMusic.getLogger().info("Discord: https://discord.gg/twQgJNufYn");
    }
}
