package com.mememan.persistency;

import com.mememan.persistency.manager.PersistencyModManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(PersistencyConstants.MOD_ID)
public class PersistencyForge {

    public PersistencyForge() {
        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
        IEventBus forgeBus = MinecraftForge.EVENT_BUS;

        if (modBus != null && forgeBus != null) PersistencyModManager.registerAll(modBus, forgeBus);

        Persistency.init();
    }
}
