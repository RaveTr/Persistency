package com.mememan.persistency.common.capabilities.base;

import com.mememan.persistency.common.registrar.CapabilityRegistrar;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

public interface ISerializableCapProvider extends ICapabilitySerializable<CompoundTag> {

    /**
     * Invalidates this capability provider's held capability (I.E. Invalidates it for further use. For more info, see {@link LazyOptional#invalidate()}).
     */
    void invalidateCap();

    /**
     * Gets the {@linkplain ResourceLocation identifier} of this capability provider's held capability.
     *
     * @return The {@linkplain ResourceLocation identifier} of this capability provider's held capability.
     */
    ResourceLocation getCapIdentifier();

    /**
     * Gets the object type to which this capability provider should be attached. Primarily used in {@link CapabilityRegistrar}.
     *
     * @return The object type to which this capability provider should be attached, represented as a {@code class}.
     */
    Class<?> getCapabilityTargetObjectType();

    /**
     * Gets a (typically {@code static}) reference to the {@linkplain Capability target capability instance} for this capability provider.
     *
     * @return A (usually {@code static}) reference to the {@linkplain Capability target capability instance} used for this capability provider.
     */
    Capability<? extends IBaseCapability> getSingletonCapabilityInstance();
}
