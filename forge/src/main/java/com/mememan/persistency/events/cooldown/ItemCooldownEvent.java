package com.mememan.persistency.events.cooldown;

import net.minecraft.world.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.Event;

/**
 * This event is fired whenever any of its child events are fired.
 * <br> </br>
 * <br> </br>
 * You can use each of these events to manipulate items at any point
 * during cooldown, and can even use both {@link ItemCooldownStartEvent} and {@link ItemCooldownTickEvent} to change the cooldown timer. 
 * You can also cancel each of these events (with the exception of {@link ItemCooldownEndEvent}) to cancel the cooldown of a specific item. 
 * <br> </br>
 * <br> </br>
 * This event and its subclasses are all fired on the {@link MinecraftForge#EVENT_BUS}.
 */
public class ItemCooldownEvent extends Event {
    private final Item targetCooldownItem;

    public ItemCooldownEvent(Item targetCooldownItem) {
        this.targetCooldownItem = targetCooldownItem;
    }

    public Item getCooldownItem() {
        return targetCooldownItem;
    }
}