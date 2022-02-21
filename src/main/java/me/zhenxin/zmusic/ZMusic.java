package me.zhenxin.zmusic;

import me.zhenxin.zmusic.event.PacketEvent;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;

public class ZMusic implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger(ZMusic.class);
    public static final Identifier IDENTIFIER = new Identifier("zmusic", "channel");

    @Override
    public void onInitialize() {
        ClientPlayNetworking.registerGlobalReceiver(
                IDENTIFIER,
                (client, handler, buf, sender) -> {
                    byte[] buffer = new byte[buf.readableBytes()];
                    buf.readBytes(buffer);
                    buffer[0] = 0;
                    String message = new String(buffer, StandardCharsets.UTF_8).substring(1);
                    PacketEvent.onPacket(message);
                }
        );
        LOGGER.info("ZMusic has been initialized.");
    }
}