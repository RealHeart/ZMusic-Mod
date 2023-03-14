package me.zhenxin.zmusic.event;

/**
 * 客户端事件
 *
 * @author 真心
 * @email qgzhenxin@qq.com
 * @since 2023/1/29 22:52
 */
class ClientEvent {

    @SuppressWarnings("AlibabaUndefineMagicConstant")
    public static void onPacket(String message) {
        if (message.startsWith("[Play]")) {
            String data = message.replace("[Play]", "");
            PacketEvent.onPlay(data);
        } else if ("[Stop]".equals(message)) {
            PacketEvent.onStop();
        }
    }

    public static void onDisconnect() {
        PacketEvent.onStop();
    }
}