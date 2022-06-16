package me.zhenxin.zmusic.event;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.util.Identifier;

/**
 * 事件处理器
 *
 * @author 真心
 * @since 2022/6/16 16:02
 */
public class EventHandler {
    public static void registry() {
        Identifier identifier = new Identifier("zmusic", "channel");
        ClientPlayNetworking.registerGlobalReceiver(identifier, ((client, handler, buf, responseSender) -> ClientEvent.onPacket(buf)));
        ClientPlayConnectionEvents.DISCONNECT.register((handler, client) -> ClientEvent.onDisconnect());
    }
}
