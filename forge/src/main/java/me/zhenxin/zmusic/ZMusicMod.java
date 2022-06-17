package me.zhenxin.zmusic;

import me.zhenxin.zmusic.event.ClientEvent;
import me.zhenxin.zmusic.event.EventActive;
import me.zhenxin.zmusic.event.EventLegacy;
import me.zhenxin.zmusic.player.MusicPlayer;
import me.zhenxin.zmusic.player.impl.SoundVolumeForge;
import net.minecraft.network.chat.ClickEvent;
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
public class ZMusicMod {


    public ZMusicMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::setup);
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(FMLClientSetupEvent event) {
        ZMusic.player = new MusicPlayer(new SoundVolumeForge());

        try {
            new EventActive();
        } catch (Exception e) {
            new EventLegacy();
        }

        ZMusic.getLogger().info("Welcome use ZMusic Mod!");
        ZMusic.getLogger().info("Homepage: https://m.zplu.cc");
        ZMusic.getLogger().info("Discord: https://discord.gg/twQgJNufYn");
    }
}
