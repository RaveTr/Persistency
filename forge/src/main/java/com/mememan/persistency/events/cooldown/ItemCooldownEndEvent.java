package com.mememan.persistency.events.cooldown;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemCooldowns;
import net.minecraftforge.eventbus.api.Cancelable;

/**
 * This event is fired when a {@link ItemCooldowns.CooldownInstance} ends on an {@link Item} (namely on the last tick). It can be used to manipulate the target {@link Item}.
 * <br> </br>
 * <br> </br>
 * This event is not {@link Cancelable}.
 */
public class ItemCooldownEndEvent extends ItemCooldownEvent {

    public ItemCooldownEndEvent(Item targetCooldownItem) {
        super(targetCooldownItem);
    }
}
