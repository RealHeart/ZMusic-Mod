package me.zhenxin.zmusic.mixin;

import lombok.extern.slf4j.Slf4j;
import net.minecraft.client.gui.screen.TitleScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Slf4j
@Mixin(TitleScreen.class)
public class ZMusicMixin {
    @Inject(at = @At("HEAD"), method = "init()V")
    private void init(CallbackInfo info) {
        log.info("ZMusic Mixin has been injected!");
    }
}
