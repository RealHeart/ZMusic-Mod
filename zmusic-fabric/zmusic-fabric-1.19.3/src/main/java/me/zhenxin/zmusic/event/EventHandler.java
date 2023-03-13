package me.zhenxin.zmusic.event;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.util.Identifier;

import java.nio.charset.StandardCharsets;

/**
 * 事件处理器
 *
 * @author 真心
 * @email qgzhenxin@qq.com
 * @since 2023/1/29 23:32
 */
public class EventHandler {

    public static void registry() {
        var identifier = new Identifier("zmusic", "channel");
        ClientPlayNetworking.registerGlobalReceiver(identifier, (client, handler, buf, responseSender) -> {
            var buffer = new byte[buf.readableBytes()];
            buf.readBytes(buffer);
            buffer[0] = 0;
            var message = new String(buffer, StandardCharsets.UTF_8).substring(1);
            ClientEvent.onPacket(message);
        });
        ClientPlayConnectionEvents.DISCONNECT.register((handler, client) -> ClientEvent.onDisconnect());
    }
}