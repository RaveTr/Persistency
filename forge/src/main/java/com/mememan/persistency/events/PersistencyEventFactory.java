package com.mememan.persistency.events;

import com.mememan.persistency.events.cooldown.ItemCooldownEndEvent;
import com.mememan.persistency.events.cooldown.ItemCooldownStartEvent;
import com.mememan.persistency.events.cooldown.ItemCooldownTickEvent;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.MinecraftForge;

public class PersistencyEventFactory {

    public static ItemCooldownStartEvent onItemCooldownStart(Item targetCooldownItem, int cooldownTicks) {
        ItemCooldownStartEvent icdsEvent = new ItemCooldownStartEvent(targetCooldownItem, cooldownTicks);

        MinecraftForge.EVENT_BUS.post(icdsEvent);

        return icdsEvent;
    }

    public static ItemCooldownTickEvent onItemCooldownTick(Item targetCooldownItem, int cooldownTicks) {
        ItemCooldownTickEvent icdtEvent = new ItemCooldownTickEvent(targetCooldownItem, cooldownTicks);

        MinecraftForge.EVENT_BUS.post(icdtEvent);

        return icdtEvent;
    }

    public static ItemCooldownEndEvent onItemCooldownEnd(Item targetCooldownItem) {
        ItemCooldownEndEvent icdeEvent = new ItemCooldownEndEvent(targetCooldownItem);

        MinecraftForge.EVENT_BUS.post(icdeEvent);

        return icdeEvent;
    }
}
