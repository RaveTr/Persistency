package com.mememan.persistency.manager;

import com.mememan.persistency.PersistencyConstants;
import com.mememan.persistency.config.common.PersistencyCommonConfig;
import com.mememan.persistency.util.FileUtil;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.loading.FMLPaths;
import org.apache.commons.lang3.tuple.Pair;

public final class PersistencyConfigManager {
    public static final ForgeConfigSpec MAIN_COMMON_SPEC;
    public static final PersistencyCommonConfig MAIN_COMMON;

    static {
        final Pair<PersistencyCommonConfig, ForgeConfigSpec> commonSpecPair = new ForgeConfigSpec.Builder().configure(PersistencyCommonConfig::new);

        MAIN_COMMON_SPEC = commonSpecPair.getRight();
        MAIN_COMMON = commonSpecPair.getLeft();
    }

    static void registerConfigs() {
        registerConfigFolder();

        registerClientConfig();
        registerCommonConfig();
        registerServerConfig();
    }

    private static void registerClientConfig() {
 //       ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, MAIN_CLIENT_SPEC, PersistencyConstants.MOD_ID + "/persistency-client.toml");
    }

    private static void registerCommonConfig() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, MAIN_COMMON_SPEC, PersistencyConstants.MOD_ID + "/persistency-common.toml");
    }

    private static void registerServerConfig() {
//        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, MAIN_SERVER_SPEC);
    }

    private static void registerConfigFolder() {
        FileUtil.getOrCreateDirectory(FMLPaths.CONFIGDIR.get().resolve(PersistencyConstants.MOD_ID), PersistencyConstants.MOD_ID);
    }
}
