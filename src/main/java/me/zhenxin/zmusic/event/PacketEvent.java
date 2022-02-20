package me.zhenxin.zmusic.event;

import lombok.extern.slf4j.Slf4j;
import me.zhenxin.zmusic.State;
import me.zhenxin.zmusic.player.MusicPlayer;

/**
 * 接收事件
 *
 * @author 真心
 * @since 2022/2/20 11:21
 */
@SuppressWarnings("AlibabaAvoidManuallyCreateThread")
@Slf4j(topic = "ZMusic")
public class PacketEvent {
    public static void onPacket(String message) {
        new Thread(() -> {
            log.debug("Received message: {}", message);
            if (message.startsWith("[Play]")) {
                String data = message.replace("[Play]", "");
                onPlay(data);
            } else if (message.startsWith("[Lyric]")) {
                String data = message.replace("[Lyric]", "");
                onLyric(data);
            } else if (message.startsWith("[Info]")) {
                String data = message.replace("[Info]", "");
                onInfo(data);
            } else if (message.startsWith("[Img]")) {
                String data = message.replace("[Img]", "");
                onImg(data);
            } else if ("[Stop]".equals(message)) {
                if (State.player != null) {
                    State.player.stop();
                }
            }
        }).start();
    }

    /**
     * 播放
     *
     * @param data 数据
     */
    public static void onPlay(String data) {
        log.info("Play music from {}", data);
        if (State.player == null) {
            State.player = new MusicPlayer();
        }
        try {
            State.player.stop();
            State.player.play(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 歌词
     *
     * @param data 数据
     */
    public static void onLyric(String data) {
        // TODO: 歌词
    }

    public static void onInfo(String data) {
        // TODO: 信息
    }

    public static void onImg(String data) {
        // TODO: 专辑图片
    }
}
