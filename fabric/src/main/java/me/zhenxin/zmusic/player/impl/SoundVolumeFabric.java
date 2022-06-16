package me.zhenxin.zmusic.player.impl;

import me.zhenxin.zmusic.player.SoundVolume;
import net.minecraft.client.MinecraftClient;
import net.minecraft.sound.SoundCategory;

/**
 * 音量控制 Fabric
 *
 * @author 真心
 * @since 2022/6/16 21:38
 */
public class SoundVolumeFabric implements SoundVolume {
    @Override
    public float getVolume() {
        return MinecraftClient.getInstance().options.getSoundVolume(SoundCategory.RECORDS);
    }
}
