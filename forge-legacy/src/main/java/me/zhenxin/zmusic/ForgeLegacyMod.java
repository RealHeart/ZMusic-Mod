package me.zhenxin.zmusic;

import io.netty.buffer.ByteBuf;
import me.zhenxin.zmusic.channel.ChannelRegistryActive;
import me.zhenxin.zmusic.event.ClientEvent;
import me.zhenxin.zmusic.player.MusicPlayer;
import me.zhenxin.zmusic.player.impl.SoundVolumeForge;
import me.zhenxin.zmusic.utils.impl.LoggerForge;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.EventBus;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

import java.nio.charset.StandardCharsets;

/**
 * @author 真心
 * @since 2022/6/18 1:44
 */
@Mod(
        value = "zmusic",
        modid = "zmusic",
        name = "ZMusic",
        clientSideOnly = true,
        acceptedMinecraftVersions = "[1.8,1.13)",
        acceptableRemoteVersions = "*"
)
public class ForgeLegacyMod {

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) throws Exception {
        ZMusic.player = new MusicPlayer(new SoundVolumeForge());
        ZMusic.setLogger(new LoggerForge());
        Class forge = MinecraftForge.class;
        try {
            IEventBus eventBus = (IEventBus) forge.getField("EVENT_BUS").get(forge);
            eventBus.register(this);
            new ChannelRegistryActive();
            ZMusic.getLogger().info("1.13-1.19");
        } catch (Throwable t) {
            EventBus eventBus = (EventBus) forge.getField("EVENT_BUS").get(forge);
            eventBus.register(this);
            NetworkRegistry.INSTANCE.newEventDrivenChannel("zmusic:channel").register(this);
            ZMusic.getLogger().info("1.8-1.12");
        }
        ZMusic.getLogger().info("Welcome use ZMusic Mod!");
        ZMusic.getLogger().info("Homepage: https://m.zplu.cc");
        ZMusic.getLogger().info("Discord: https://discord.gg/twQgJNufYn");
    }

    @SubscribeEvent
    public void onDisconnect(FMLNetworkEvent.ClientDisconnectionFromServerEvent e) {
        ClientEvent.onDisconnect();
    }

    @SubscribeEvent
    public void onClientPacket(FMLNetworkEvent.ClientCustomPacketEvent e) {
        ByteBuf directBuf = e.getPacket().payload();
        byte[] array = new byte[directBuf.readableBytes()];
        directBuf.getBytes(directBuf.readerIndex(), array);
        array[0] = 0;
        String message = new String(array, StandardCharsets.UTF_8).substring(1);
        ClientEvent.onPacket(message);
    }
}
