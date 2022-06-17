package me.zhenxin.zmusic.event;

import com.goxr3plus.streamplayer.enums.Status;
import me.zhenxin.zmusic.ZMusic;
import net.minecraft.sounds.SoundSource;
import net.minecraftforge.client.event.ClientPlayerNetworkEvent;
import net.minecraftforge.client.event.sound.SoundEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

/**
 * 客户端事件
 *
 * @author 真心
 * @since 2022/6/16 20:03
 */
@SuppressWarnings({"AlibabaAvoidManuallyCreateThread", "AlibabaUndefineMagicConstant"})
public class ClientEvent {

    @SubscribeEvent
    public void onDisconnect(final ClientPlayerNetworkEvent.LoggedOutEvent e) {
        PacketEvent.onStop();
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

    public static void onPacket(String message) {
        ZMusic.getLogger().info("Received message: {}", message);

        if (message.startsWith("[Play]")) {
            String data = message.replace("[Play]", "");
            PacketEvent.onPlay(data);
        } else if (message.startsWith("[Lyric]")) {
            String data = message.replace("[Lyric]", "");
            PacketEvent.onLyric(data);
        } else if (message.startsWith("[Info]")) {
            String data = message.replace("[Info]", "");
            PacketEvent.onInfo(data);
        } else if (message.startsWith("[Img]")) {
            String data = message.replace("[Img]", "");
            PacketEvent.onImg(data);
        } else if ("[Pause]".equals(message)) {
            PacketEvent.onPause();
        } else if ("[Resume]".equals(message)) {
            PacketEvent.onResume();
        } else if ("[Stop]".equals(message)) {
            PacketEvent.onStop();
        }
    }
}
