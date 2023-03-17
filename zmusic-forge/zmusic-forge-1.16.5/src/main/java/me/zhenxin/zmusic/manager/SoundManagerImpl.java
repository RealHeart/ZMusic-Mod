package me.zhenxin.zmusic.manager;

import net.minecraft.client.Minecraft;
import net.minecraft.util.SoundCategory;

/**
 * 音频管理器实现
 *
 * @author 真心
 * @email qgzhenxin@qq.com
 * @since 2023/1/29 23:29
 */
public class SoundManagerImpl implements SoundManager {

    @Override
    public float volume() {
        return Minecraft.getInstance().options.getSoundSourceVolume(SoundCategory.RECORDS);
    }

    @Override
    public void stop() {
        Minecraft.getInstance().getSoundManager().stop(null, SoundCategory.MUSIC);
        Minecraft.getInstance().getSoundManager().stop(null, SoundCategory.RECORDS);
    }
}