package me.zhenxin.zmusic.event;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.nio.charset.StandardCharsets;

/**
 * 事件处理器
 *
 * @author 真心
 * @since 2022/6/16 16:02
 */
public class EventHandler {
    public static void registry() {
        Identifier identifier = new Identifier("zmusic", "channel");
        ClientPlayNetworking.registerGlobalReceiver(identifier, (client, handler, buf, responseSender) -> {
            byte[] buffer = new byte[buf.readableBytes()];
            buf.readBytes(buffer);
            buffer[0] = 0;
            String message = new String(buffer, StandardCharsets.UTF_8).substring(1);
            ClientEvent.onPacket(message);
        });
        ClientPlayConnectionEvents.DISCONNECT.register((handler, client) -> ClientEvent.onDisconnect());
    }
}
