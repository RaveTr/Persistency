package com.mememan.persistency.manager;

import net.minecraftforge.eventbus.api.IEventBus;

public final class PersistencyModManager {

    public static void registerAll(IEventBus modBus, IEventBus forgeBus) {
        PersistencyConfigManager.registerConfigs();
        PersistencyEventManager.registerEvents(modBus, forgeBus);
    }
}
