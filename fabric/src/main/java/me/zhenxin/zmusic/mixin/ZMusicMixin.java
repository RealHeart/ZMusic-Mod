package me.zhenxin.zmusic.mixin;

import me.zhenxin.zmusic.ZMusic;
import net.minecraft.client.gui.screen.TitleScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * ZMusicMixin
 *
 * @author 真心
 * @since 2022/6/15 20:21
 */
@Mixin(TitleScreen.class)
class ZMusicMixin {
    @Inject(method = "init()V", at = @At("HEAD"))
    public void init(CallbackInfo info) {
        ZMusic.getLogger().info("ZMusic Mixin has been injected!");
    }
}