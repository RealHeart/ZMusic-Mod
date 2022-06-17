package me.zhenxin.zmusic.player.impl;

import me.zhenxin.zmusic.player.SoundVolume;
import net.minecraft.client.Minecraft;
import net.minecraft.sounds.SoundSource;

/**
 * 音量控制 Fabric
 *
 * @author 真心
 * @since 2022/6/16 21:38
 */
public class SoundVolumeForge implements SoundVolume {
    @Override
    public float getVolume() {
        return Minecraft.getInstance().options.getSoundSourceVolume(SoundSource.RECORDS);
    }
}
