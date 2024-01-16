package com.mememan.persistency.common.capabilities.cooldown;

import com.mememan.persistency.common.capabilities.base.IBaseCapability;
import net.minecraftforge.common.capabilities.AutoRegisterCapability;

@AutoRegisterCapability
public interface ICooldownCapability extends IBaseCapability {
    String COOLDOWN_CAP_NAME = "persistent_cooldown";
    String START_TICK_DATA_KEY = "startTick";
    String CUR_COOLDOWN_TICK_DATA_KEY = "curCooldownTick";
    String END_TICK_DATA_KEY = "endTick";

    /**
     * Gets the start time of the cooldown (typically the current game tick).
     *
     * @return The start time of the cooldown (in ticks).
     */
    int getCooldownStartTime();

    void setCooldownStartTime(int curTick);

    int getCooldownTick();

    void setCooldownTick(int curTick);

    int getCooldownEndTime();

    void setCooldownEndTime(int curTick);

    @Override
    default String getName() {
        return COOLDOWN_CAP_NAME;
    }
}
