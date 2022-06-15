package me.zhenxin.zmusic;

import net.minecraftforge.fml.common.Mod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ZMusic Mod 主入口 Forge
 *
 * @author 真心
 * @since 2022/6/15 22:22
 */
@Mod("zmusic")
public class ZMusic {

    public static Logger getLogger() {
        return LoggerFactory.getLogger(ZMusic.class);
    }
}
