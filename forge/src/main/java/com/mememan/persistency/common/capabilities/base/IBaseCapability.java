package com.mememan.persistency.common.capabilities.base;

import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.util.INBTSerializable;

public interface IBaseCapability extends INBTSerializable<CompoundTag> {
    /**
     * The name under which the capability object implementing this interface should be registered.
     *
     * @return This capability instance's name.
     */
    String getName();
}
