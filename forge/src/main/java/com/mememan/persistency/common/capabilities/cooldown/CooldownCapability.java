package com.mememan.persistency.common.capabilities.cooldown;

import com.mememan.persistency.common.registrar.CapabilityRegistrar;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;

public class CooldownCapability implements ICooldownCapability {
    public static final Capability<ICooldownCapability> INSTANCE = CapabilityManager.get(new CapabilityToken<>() {});
    private int startTick = 0;
    private int curCooldownTick = 0;
    private int endTick = 0;

    public CooldownCapability() {
        CapabilityRegistrar.registerCapabilityProvider(new CooldownCapabilityProvider());
    }

    @Override
    public int getCooldownStartTime() {
        return startTick;
    }

    @Override
    public void setCooldownStartTime(int curTick) {
        this.startTick = curTick;
    }

    @Override
    public int getCooldownTick() {
        return curCooldownTick;
    }

    @Override
    public void setCooldownTick(int curTick) {
        this.curCooldownTick = curTick;
    }

    @Override
    public int getCooldownEndTime() {
        return endTick;
    }

    @Override
    public void setCooldownEndTime(int curTick) {
        this.endTick = curTick;
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag heldCooldownDataTag = new CompoundTag();

        heldCooldownDataTag.putInt(START_TICK_DATA_KEY, startTick);
        heldCooldownDataTag.putInt(CUR_COOLDOWN_TICK_DATA_KEY, curCooldownTick);
        heldCooldownDataTag.putInt(END_TICK_DATA_KEY, endTick);

        return heldCooldownDataTag;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        this.startTick = nbt.getInt(START_TICK_DATA_KEY);
        this.curCooldownTick = nbt.getInt(CUR_COOLDOWN_TICK_DATA_KEY);
        this.endTick = nbt.getInt(END_TICK_DATA_KEY);
    }
}
