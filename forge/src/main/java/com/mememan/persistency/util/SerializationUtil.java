package com.mememan.persistency.util;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemCooldowns;

public final class SerializationUtil {

    private SerializationUtil() {
        throw new IllegalAccessError("Attempted to construct a Utility Class!");
    }

    public static CompoundTag serializeCooldown(ItemCooldowns.CooldownInstance targetSerializableCooldown) {
        CompoundTag targetCooldownDataTag = new CompoundTag();

        targetCooldownDataTag.putInt("startCooldownTick", targetSerializableCooldown.startTime);
        targetCooldownDataTag.putInt("endCooldownTick", targetSerializableCooldown.endTime);

        return targetCooldownDataTag;
    }

    public static ItemCooldowns.CooldownInstance deserializeCooldown(CompoundTag targetCooldownDataTag) {
        return new ItemCooldowns.CooldownInstance(targetCooldownDataTag.getInt("startCooldownTick"), targetCooldownDataTag.getInt("endCooldownTick"));
    }
}
