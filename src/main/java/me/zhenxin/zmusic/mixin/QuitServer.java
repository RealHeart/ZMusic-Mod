package me.zhenxin.zmusic.mixin;

import me.zhenxin.zmusic.event.ClientEvent;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * 退出服务器
 *
 * @author 真心
 * @since 2022/2/21 10:43
 */
@Mixin(MinecraftClient.class)
public class QuitServer {
    @Inject(method = "disconnect(Lnet/minecraft/client/gui/screen/Screen;)V", at = @At("TAIL"))
    public void quit(CallbackInfo info) {
        ClientEvent.onServerQuit();
    }
}
