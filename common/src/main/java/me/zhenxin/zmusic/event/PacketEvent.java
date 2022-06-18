package me.zhenxin.zmusic.event;

import com.goxr3plus.streamplayer.enums.Status;
import me.zhenxin.zmusic.ZMusic;

import java.net.URL;

/**
 * 发包事件
 *
 * @author 真心
 * @since 2022/6/16 20:04
 */
@SuppressWarnings("AlibabaAvoidManuallyCreateThread")
public class PacketEvent {
    /**
     * 播放
     *
     * @param data 数据
     */
    public static void onPlay(String data) {
        ZMusic.getLogger().info("Play music from " + data);

        if (ZMusic.player.getStatus() == Status.PLAYING) {
            ZMusic.player.stop();
        }
        new Thread(() -> {
            try {
                ZMusic.player.open(new URL(data));
                ZMusic.player.play();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    /**
     * 暂停
     */
    public static void onPause() {
        if (ZMusic.player.getStatus() == Status.PLAYING) {
            ZMusic.player.pause();
        }
    }

    /**
     * 恢复
     */
    public static void onResume() {
        if (ZMusic.player.getStatus() == Status.PAUSED) {
            ZMusic.player.resume();
        }
    }

    /**
     * 停止
     */
    public static void onStop() {
        if (ZMusic.player.getStatus() != Status.STOPPED) {
            ZMusic.player.stop();
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

    /**
     * 信息
     *
     * @param data 数据
     */
    public static void onInfo(String data) {
        // TODO: 信息
    }

    /**
     * 图片
     *
     * @param data 数据
     */
    public static void onImg(String data) {
        // TODO: 专辑图片
    }
}
