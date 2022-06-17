package me.zhenxin.zmusic.mixin;

import com.goxr3plus.streamplayer.enums.Status;
import me.zhenxin.zmusic.ZMusic;
import net.minecraft.client.sound.SoundInstance;
import net.minecraft.client.sound.SoundSystem;
import net.minecraft.sound.SoundCategory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * 声音事件
 *
 * @author 真心
 * @since 2022/6/17 20:53
 */
@Mixin(SoundSystem.class)
public class SoundEvent {
    @Inject(method = "play(Lnet/minecraft/client/sound/SoundInstance;)V", at = @At("HEAD"))
    public void sound(SoundInstance soundInstance, CallbackInfo info) {
        if (ZMusic.player.getStatus() == Status.PLAYING) {
            SoundCategory data = soundInstance.getCategory();
            if (data == SoundCategory.MUSIC || data == SoundCategory.RECORDS) {
                info.cancel();
            }
        }
    }
}
