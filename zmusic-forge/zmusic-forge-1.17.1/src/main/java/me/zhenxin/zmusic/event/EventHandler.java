package me.zhenxin.zmusic.event;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fmllegacy.network.NetworkRegistry;
import net.minecraftforge.fmllegacy.network.NetworkEvent;
import net.minecraftforge.fmllegacy.network.simple.SimpleChannel;

import java.nio.charset.StandardCharsets;
import java.util.function.Supplier;

/**
 * 事件处理器
 *
 * @author 真心
 * @email qgzhenxin@qq.com
 * @since 2023/1/29 23:32
 */
public class EventHandler {

    public static void registry() {
        SimpleChannel channel = NetworkRegistry.newSimpleChannel(new ResourceLocation("zmusic", "channel"),
                () -> "1.0", s -> true, s -> true);
        channel.registerMessage(666, String.class, EventHandler::enc, EventHandler::dec, EventHandler::proc);
    }

    private static void enc(String str, FriendlyByteBuf buffer) {
        buffer.writeBytes(str.getBytes(StandardCharsets.UTF_8));
    }


    private static String dec(FriendlyByteBuf buffer) {
        return buffer.toString(StandardCharsets.UTF_8);
    }

    private static void proc(String message, Supplier<NetworkEvent.Context> supplier) {
        ClientEvent.onPacket(message);
        NetworkEvent.Context context = supplier.get();
        context.setPacketHandled(true);
    }
}