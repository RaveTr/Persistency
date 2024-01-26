package com.mememan.persistency.mixins;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemCooldowns;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Map;

@Mixin(ItemCooldowns.class)
public interface IItemCooldownsAccessor {
    @Accessor("cooldowns")
    Map<Item, ItemCooldowns.CooldownInstance> getCooldowns();
}
