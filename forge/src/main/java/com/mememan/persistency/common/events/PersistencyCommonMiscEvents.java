package com.mememan.persistency.common.events;

import com.mememan.persistency.common.capabilities.cooldown.CooldownCapability;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class PersistencyCommonMiscEvents {

    @SubscribeEvent
    public static void onPlayerLoggedInEvent(PlayerEvent.PlayerLoggedInEvent event) {
        Player targetPlayer = event.getEntity();

        if (targetPlayer != null) {
            targetPlayer.getCapability(CooldownCapability.INSTANCE).ifPresent(cooldownCap -> {
                
            });
        }
    }
}
