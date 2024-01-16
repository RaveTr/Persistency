package com.mememan.persistency.events.cooldown;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemCooldowns;
import net.minecraftforge.eventbus.api.Cancelable;

/**
 * This event is fired when a {@link ItemCooldowns.CooldownInstance} starts on an {@link Item} (namely on the first tick). It can be used to manipulate the target {@link Item}/{@link ItemCooldowns.CooldownInstance},
 * or outright remove the {@link ItemCooldowns.CooldownInstance} from the {@link Item}.
 * <br> </br>
 * <br> </br>
 * This event is {@link Cancelable}.
 */
@Cancelable
public class ItemCooldownStartEvent extends ItemCooldownEvent {
    private int cooldownTicks;

    public ItemCooldownStartEvent(Item targetCooldownItem, int cooldownTicks) {
        super(targetCooldownItem);
        this.cooldownTicks = cooldownTicks;
    }

    public int getCooldownTicks() {
        return cooldownTicks;
    }

    public void setCooldownTicks(int cooldownTicks) {
        this.cooldownTicks = cooldownTicks;
    }
}
