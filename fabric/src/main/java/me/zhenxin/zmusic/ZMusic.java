package me.zhenxin.zmusic;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ZMusic Mod 主入口 Fabric
 *
 * @author 真心
 * @since 2022/6/15 19:47
 */
public class ZMusic implements ModInitializer {

    @Override
    public void onInitialize() {
        getLogger().info("ZMusic Loaded!");
    }

    public static Logger getLogger() {
        return LoggerFactory.getLogger(ZMusic.class);
    }
}