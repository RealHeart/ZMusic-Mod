package me.zhenxin.zmusic;

import net.minecraftforge.client.event.ClientPlayerNetworkEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * ZMusic Mod 主入口 Forge
 *
 * @author 真心
 * @since 2022/6/15 22:22
 */
@Mod("zmusic")
public class ZMusicMod {

    @SubscribeEvent
    public void onServerQuit(final ClientPlayerNetworkEvent.LoggedOutEvent e) {
        ZMusic.getLogger().info("disconnect server");
    }
}
