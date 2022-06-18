package me.zhenxin.zmusic.utils;

/**
 * @author 真心
 * @since 2022/6/18 20:33
 */
public interface Logger {

    /**
     * 信息
     *
     * @param message 消息
     */
    public void info(String message);

    /**
     * 调试
     *
     * @param message 消息
     */
    public void debug(String message);

    /**
     * 警告
     *
     * @param message 消息
     */
    public void warn(String message);
}
