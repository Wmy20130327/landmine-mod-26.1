package com.wmy.landmine;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LandmineMod implements ModInitializer {
    public static final String MOD_ID = "landmine";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LandmineBlocks.register();
        LandmineItems.register();
        LOGGER.info("地雷模组初始化完成！");
    }
}
