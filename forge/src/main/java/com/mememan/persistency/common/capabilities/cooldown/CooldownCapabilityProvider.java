package com.mememan.persistency.common.capabilities.cooldown;

import com.mememan.persistency.PersistencyConstants;
import com.mememan.persistency.common.capabilities.base.ISerializableCapProvider;
import com.mememan.persistency.common.registrar.CapabilityRegistrar;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CooldownCapabilityProvider implements ISerializableCapProvider {
    public static final ResourceLocation COOLDOWN_CAP_IDENTIFIER = new ResourceLocation(PersistencyConstants.MOD_ID, "cooldown_cap");
    private final ICooldownCapability cooldownCapObj = new CooldownCapability();
    private final LazyOptional<ICooldownCapability> lazyCooldownCapHolder = LazyOptional.of(() -> cooldownCapObj);

    public CooldownCapabilityProvider() {
    }

    @Override
    public void invalidateCap() {
        lazyCooldownCapHolder.invalidate();
    }

    @Override
    public ResourceLocation getCapIdentifier() {
        return COOLDOWN_CAP_IDENTIFIER;
    }

    @Override
    public Class<Player> getCapabilityTargetObjectType() {
        return Player.class;
    }

    @Override
    public Capability<? extends ICooldownCapability> getSingletonCapabilityInstance() {
        return CooldownCapability.INSTANCE;
    }

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        return CooldownCapability.INSTANCE.orEmpty(cap, lazyCooldownCapHolder);
    }

    @Override
    public CompoundTag serializeNBT() {
        return cooldownCapObj.serializeNBT();
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        cooldownCapObj.deserializeNBT(nbt);
    }
}
