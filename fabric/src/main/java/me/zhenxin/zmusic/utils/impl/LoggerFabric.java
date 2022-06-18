package me.zhenxin.zmusic.utils.impl;

import me.zhenxin.zmusic.ZMusic;
import me.zhenxin.zmusic.utils.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author 真心
 * @since 2022/6/18 20:39
 */
public class LoggerFabric implements Logger {
    private org.slf4j.Logger logger = LoggerFactory.getLogger(ZMusic.class);

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
