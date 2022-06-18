package net.minecraftforge.fml.common.network;

import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.fml.common.network.internal.FMLProxyPacket;

public class FMLNetworkEvent extends Event {
    public static class ClientDisconnectionFromServerEvent extends FMLNetworkEvent {
    }

    public static class ClientCustomPacketEvent extends CustomPacketEvent {
        protected ClientCustomPacketEvent(FMLProxyPacket packet) {
            super(packet);
        }
    }

    public static abstract class CustomPacketEvent extends FMLNetworkEvent {
        private final FMLProxyPacket packet;

        protected CustomPacketEvent(FMLProxyPacket packet) {
            this.packet = packet;
        }

        public FMLProxyPacket getPacket() {
            return packet;
        }
    }
}
