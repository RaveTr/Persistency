package com.mememan.persistency.common.events;

import com.mememan.persistency.common.capabilities.cooldown.CooldownCapability;
import com.mememan.persistency.mixins.IItemCooldownsAccessor;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemCooldowns;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.Map;

public class PersistencyCommonMiscEvents {

    @SubscribeEvent
    public static void onPlayerLoggedInEvent(PlayerEvent.PlayerLoggedInEvent event) {
        Player targetPlayer = event.getEntity();

        if (targetPlayer != null && targetPlayer instanceof ServerPlayer targetServerPlayer) {
            targetServerPlayer.getCapability(CooldownCapability.INSTANCE).ifPresent(cooldownCap -> {
                ItemCooldowns capTracker = cooldownCap.getCurTracker();

                if (capTracker != null) {
                    ItemCooldowns playerTracker = targetPlayer.getCooldowns();

                    if (playerTracker != null && playerTracker instanceof IItemCooldownsAccessor trackerAccessor && capTracker instanceof IItemCooldownsAccessor capTrackerAccessor) {
                        Map<Item, ItemCooldowns.CooldownInstance> trackerCooldowns = trackerAccessor.getCooldowns();
                        Map<Item, ItemCooldowns.CooldownInstance> capTrackerCooldowns = capTrackerAccessor.getCooldowns();

                        trackerCooldowns.clear(); // Just in case :HEHEHEHA:
                        trackerCooldowns.putAll(capTrackerCooldowns);
                    }
                } else cooldownCap.setCurTracker(targetPlayer.getCooldowns());
            });
        }
    }
}
