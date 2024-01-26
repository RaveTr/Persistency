package com.mememan.persistency.manager;

import com.mememan.persistency.common.events.PersistencyCommonMiscEvents;
import com.mememan.persistency.common.events.PersistencyCommonSetupEvents;
import net.minecraftforge.eventbus.api.IEventBus;

public final class PersistencyEventManager {

    static void registerEvents(IEventBus modBus, IEventBus forgeBus) {
        registerClientEvents(modBus, forgeBus);
        registerCommonEvents(modBus, forgeBus);
        registerServerEvents(modBus, forgeBus);
    }

    private static void registerClientEvents(IEventBus modBus, IEventBus forgeBus) {

    }

    private static void registerCommonEvents(IEventBus modBus, IEventBus forgeBus) {
        modBus.register(PersistencyCommonSetupEvents.ModSetupEvents.class);

        forgeBus.register(PersistencyCommonSetupEvents.ForgeSetupEvents.class);
        forgeBus.register(PersistencyCommonMiscEvents.class);
    }

    private static void registerServerEvents(IEventBus modBus, IEventBus forgeBus) {

    }
}
