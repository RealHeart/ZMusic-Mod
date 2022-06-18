package me.zhenxin.zmusic.event;

import me.zhenxin.zmusic.ZMusic;

/**
 * 客户端事件
 *
 * @author 真心
 * @since 2022/6/16 20:03
 */
@SuppressWarnings({"AlibabaAvoidManuallyCreateThread", "AlibabaUndefineMagicConstant"})
public class ClientEvent {

    public static void onDisconnect() {
        PacketEvent.onStop();
    }

    public static void onPacket(String message) {
        ZMusic.getLogger().info("Received message: " + message);

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
