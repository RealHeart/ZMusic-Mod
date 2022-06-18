package me.zhenxin.zmusic.event;

import com.goxr3plus.streamplayer.enums.Status;
import me.zhenxin.zmusic.ZMusic;
import net.minecraft.sounds.SoundSource;
import net.minecraftforge.client.event.ClientPlayerNetworkEvent;
import net.minecraftforge.client.event.sound.SoundEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

/**
 * @author 真心
 * @since 2022/6/18 20:57
 */
public class ForgeEvent {

    @SubscribeEvent
    public void onDisconnect(final ClientPlayerNetworkEvent.LoggedOutEvent e) {
        ClientEvent.onDisconnect();
    }

    @SubscribeEvent
    public void onSound(final SoundEvent.SoundSourceEvent e) {
        if (ZMusic.player.getStatus() == Status.PLAYING) {
            SoundSource data = e.getSound().getSource();
            if (data == SoundSource.MUSIC || data == SoundSource.RECORDS) {
                e.getChannel().stop();
            }
        }
    }
}
