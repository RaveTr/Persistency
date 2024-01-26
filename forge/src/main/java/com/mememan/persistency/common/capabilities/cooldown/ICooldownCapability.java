package com.mememan.persistency.common.capabilities.cooldown;

import com.mememan.persistency.common.capabilities.base.IBaseCapability;
import net.minecraft.world.item.ItemCooldowns;
import net.minecraftforge.common.capabilities.AutoRegisterCapability;

@AutoRegisterCapability
public interface ICooldownCapability extends IBaseCapability {
    String COOLDOWN_CAP_NAME = "persistent_cooldown";
    String START_TICK_DATA_KEY = "startTick";
    String CUR_COOLDOWN_TICK_DATA_KEY = "curCooldownTick";
    String END_TICK_DATA_KEY = "endTick";

    ItemCooldowns getCurTracker();

    void setCurTracker(ItemCooldowns curTracker);

    @Override
    default String getName() {
        return COOLDOWN_CAP_NAME;
    }
}
