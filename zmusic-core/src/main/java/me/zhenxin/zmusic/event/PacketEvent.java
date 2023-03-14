package me.zhenxin.zmusic.event;

import com.goxr3plus.streamplayer.enums.Status;
import lombok.extern.log4j.Log4j2;
import me.zhenxin.zmusic.ZMusic;

import java.net.URL;


/**
 * 发包事件
 *
 * @author 真心
 * @email qgzhenxin@qq.com
 * @since 2023/1/29 22:50
 */
@Log4j2
class PacketEvent {

    @SuppressWarnings("AlibabaAvoidManuallyCreateThread")
    public static void onPlay(String data) {
        log.info("Play music from {}", data);
        if (ZMusic.getPlayer().getStatus() == Status.PLAYING) {
            ZMusic.getPlayer().stop();
        }
        Thread thread = new Thread(() -> {
            try {
                URL url = new URL(data);
                ZMusic.getPlayer().open(url);
                ZMusic.getPlayer().play();
            } catch (Exception e) {
                e.printStackTrace();
                log.error("Failed to play music! {}", e.getMessage());
            }
        });
        thread.setName("ZMusic player thread");
        thread.start();
    }

    public static void onPause() {
        if (ZMusic.getPlayer().getStatus() == Status.PLAYING) {
            ZMusic.getPlayer().pause();
        }
    }

    public static void onResume() {
        if (ZMusic.getPlayer().getStatus() == Status.PAUSED) {
            ZMusic.getPlayer().resume();
        }
    }

    public static void onStop() {
        if (ZMusic.getPlayer().getStatus() != Status.STOPPED) {
            ZMusic.getPlayer().stop();
        }
    }

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