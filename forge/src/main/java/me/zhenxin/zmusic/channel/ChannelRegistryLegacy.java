package me.zhenxin.zmusic.channel;

import me.zhenxin.zmusic.event.ClientEvent;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fmllegacy.network.NetworkEvent;
import net.minecraftforge.fmllegacy.network.NetworkRegistry;
import net.minecraftforge.fmllegacy.network.simple.SimpleChannel;

import java.nio.charset.StandardCharsets;
import java.util.function.Supplier;

/**
 * @author 真心
 * @since 2022/6/17 20:20
 */
public class ChannelRegistryLegacy {
    public ChannelRegistryLegacy(){
        SimpleChannel channel = NetworkRegistry.newSimpleChannel(new ResourceLocation("zmusic", "channel"),
                () -> "1.0", s -> true, s -> true);
        channel.registerMessage(666, String.class, this::enc, this::dec, this::proc);
    }

    private void enc(String str, FriendlyByteBuf buffer) {
        buffer.writeBytes(str.getBytes(StandardCharsets.UTF_8));
    }

    private String dec(FriendlyByteBuf buffer) {
        return buffer.toString(StandardCharsets.UTF_8);
    }

    private void proc(String message, Supplier<NetworkEvent.Context> supplier) {
        ClientEvent.onPacket(message);
        NetworkEvent.Context context = supplier.get();
        context.setPacketHandled(true);
    }
}
