package me.zhenxin.zmusic;

import lombok.extern.slf4j.Slf4j;
import me.zhenxin.zmusic.event.PacketEvent;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.util.Identifier;

import java.nio.charset.StandardCharsets;

@Slf4j(topic = "ZMusic")
public class ZMusic implements ModInitializer {
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
        log.info("ZMusic has been initialized.");
    }
}