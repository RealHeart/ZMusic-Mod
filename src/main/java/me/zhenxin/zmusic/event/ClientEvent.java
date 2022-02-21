package me.zhenxin.zmusic.event;

import me.zhenxin.zmusic.State;

/**
 * 客户端事件
 *
 * @author 真心
 * @since 2022/2/21 10:44
 */
public class ClientEvent {
    public static void onServerQuit() {
        if (State.player != null) {
            State.player.stop();
        }
    }
}
