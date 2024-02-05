package com.mememan.persistency.common.capabilities.cooldown;

import com.mememan.persistency.common.capabilities.base.IBaseCapability;
import net.minecraft.world.item.ItemCooldowns;
import net.minecraftforge.common.capabilities.AutoRegisterCapability;

@AutoRegisterCapability
public interface ICooldownCapability extends IBaseCapability {
    String COOLDOWN_CAP_NAME = "persistent_cooldown";
    String COOLDOWN_ITEM_KEYSET_DATA_KEY = "cooldownItemKeyset";
    String COOLDOWN_INSTANCE_VALUESET_DATA_KEY = "cooldownInstanceValueset";

    ItemCooldowns getCurTracker();

    void setCurTracker(ItemCooldowns curTracker);

    @Override
    default String getName() {
        return COOLDOWN_CAP_NAME;
    }
}
