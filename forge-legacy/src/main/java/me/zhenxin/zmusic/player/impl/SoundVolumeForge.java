package me.zhenxin.zmusic.player.impl;

import me.zhenxin.zmusic.ZMusic;
import me.zhenxin.zmusic.player.SoundVolume;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.util.SoundCategory;

/**
 * 音量控制 Fabric
 *
 * @author 真心
 * @since 2022/6/16 21:38
 */
public class SoundVolumeForge implements SoundVolume {
    @Override
    public float getVolume() {
        ZMusic.getLogger().info("我无语了");
        try {
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
    }
}
