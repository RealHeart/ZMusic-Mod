package net.minecraftforge.fml.common.network;


public enum NetworkRegistry {
    INSTANCE;

    public FMLEventChannel newEventDrivenChannel(String name) {
        return new FMLEventChannel();
    }
}