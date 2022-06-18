package me.zhenxin.zmusic.utils.impl;

import me.zhenxin.zmusic.ZMusic;
import me.zhenxin.zmusic.utils.Logger;
import org.apache.logging.log4j.LogManager;

/**
 * @author 真心
 * @since 2022/6/18 20:41
 */
public class LoggerForge implements Logger {
    private org.apache.logging.log4j.Logger logger = LogManager.getLogger(ZMusic.class);

    @Override
    public void info(String message) {
        logger.info(message);
    }

    @Override
    public void debug(String message) {
        logger.debug(message);
    }

    @Override
    public void warn(String message) {
        logger.warn(message);
    }
}
