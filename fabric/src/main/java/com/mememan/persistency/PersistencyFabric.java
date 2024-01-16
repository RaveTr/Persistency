package com.mememan.persistency;

import net.fabricmc.api.ModInitializer;

public class PersistencyFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        Persistency.init();
    }
}
