package net.minecraft.util;

/**
 * @author 真心
 * @since 2022/6/18 22:22
 */
public enum SoundCategory {
    MASTER("master"),
    MUSIC("music"),
    RECORDS("record"),
    WEATHER("weather"),
    BLOCKS("block"),
    HOSTILE("hostile"),
    NEUTRAL("neutral"),
    PLAYERS("player"),
    AMBIENT("ambient"),
    VOICE("voice");
    private final String name;

    private SoundCategory(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
