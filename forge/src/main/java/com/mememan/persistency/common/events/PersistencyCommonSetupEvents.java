package com.mememan.persistency.common.events;

import com.mememan.persistency.common.registrar.CapabilityRegistrar;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;


public class PersistencyCommonSetupEvents {

    public static class ModSetupEvents {

    }

    public static class ForgeSetupEvents {

        @SubscribeEvent
        public static void onAttachCapabilitiesEvent(AttachCapabilitiesEvent<Entity> event) {
            CapabilityRegistrar.runAndEmptyRegistrar((capProvider) -> {
                if (!event.getCapabilities().containsKey(capProvider.getCapIdentifier()) && !event.getObject().getCapability(capProvider.getSingletonCapabilityInstance()).isPresent()) event.addCapability(capProvider.getCapIdentifier(), capProvider);
            }, (capProvider) -> capProvider.getCapabilityTargetObjectType().isAssignableFrom(event.getObject().getClass()));
        }

        @SubscribeEvent
        public static void onPlayerCloneEvent(PlayerEvent.Clone event) {

        }
    }
}
