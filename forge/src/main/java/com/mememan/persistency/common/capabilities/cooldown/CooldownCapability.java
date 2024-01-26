package com.mememan.persistency.common.capabilities.cooldown;

import com.mememan.persistency.common.registrar.CapabilityRegistrar;
import com.mememan.persistency.mixins.IItemCooldownsAccessor;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemCooldowns;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;

import java.util.Map;

public class CooldownCapability implements ICooldownCapability {
    public static final Capability<ICooldownCapability> INSTANCE = CapabilityManager.get(new CapabilityToken<>() {});
    private ItemCooldowns curTracker;

    public CooldownCapability() {
        CapabilityRegistrar.registerCapabilityProvider(new CooldownCapabilityProvider());
    }

    @Override
    public ItemCooldowns getCurTracker() {
        return curTracker;
    }

    @Override
    public void setCurTracker(ItemCooldowns curTracker) {
        this.curTracker = curTracker;
    }

    @Override
    public CompoundTag serializeNBT() { //TODO Packet sync w/setter S2C
        CompoundTag heldCooldownDataTag = new CompoundTag();
        ListTag heldCooldownDataListTag = new ListTag();



        return heldCooldownDataTag;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        ListTag heldCooldownDataTag = nbt.getList(CUR_COOLDOWN_TICK_DATA_KEY, 10);

        ItemCooldowns curTracker = new ItemCooldowns();

        if (curTracker instanceof IItemCooldownsAccessor trackerAccessor) {
            Map<Item, ItemCooldowns.CooldownInstance> trackerCooldowns = trackerAccessor.getCooldowns();

            trackerCooldowns.clear(); // Just in case :HEHEHEHA:

        }
    }
}
