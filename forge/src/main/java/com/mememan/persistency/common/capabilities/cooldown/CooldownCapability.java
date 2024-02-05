package com.mememan.persistency.common.capabilities.cooldown;

import com.mememan.persistency.common.registrar.CapabilityRegistrar;
import com.mememan.persistency.mixins.IItemCooldownsAccessor;
import com.mememan.persistency.util.CollectionUtil;
import com.mememan.persistency.util.SerializationUtil;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemCooldowns;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;

import java.util.List;
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
    public CompoundTag serializeNBT() { //TODO MapTag impl. Ordering seems to work (since K-V index is ensured regardless of actual list index), but this is a bit messy/icky :p
        CompoundTag heldCooldownDataTag = new CompoundTag();
        ListTag heldCooldownItemListTag = new ListTag();
        ListTag heldCooldownInstanceListTag = new ListTag();

        if (curTracker instanceof IItemCooldownsAccessor trackerAccessor) {
            trackerAccessor.getCooldowns().forEach((targetItem, targetItemCooldown) -> {
                heldCooldownItemListTag.add(targetItem.getDefaultInstance().serializeNBT());
                heldCooldownInstanceListTag.add(SerializationUtil.serializeCooldown(targetItemCooldown));
            });
        }

        heldCooldownDataTag.put(COOLDOWN_ITEM_KEYSET_DATA_KEY, heldCooldownItemListTag);
        heldCooldownDataTag.put(COOLDOWN_INSTANCE_VALUESET_DATA_KEY, heldCooldownInstanceListTag);

        return heldCooldownDataTag;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        ListTag heldCooldownItemListTag = nbt.getList(COOLDOWN_ITEM_KEYSET_DATA_KEY, 10);
        ListTag heldCooldownInstanceListTag = nbt.getList(COOLDOWN_INSTANCE_VALUESET_DATA_KEY, 10);
        ObjectArrayList<Item> heldCooldownItems = Util.make(new ObjectArrayList<>(heldCooldownItemListTag.size()), (targetItemList) -> heldCooldownItemListTag.forEach((targetItemTag) -> targetItemList.add(ItemStack.of((CompoundTag) targetItemTag).getItem())));
        ObjectArrayList<ItemCooldowns.CooldownInstance> heldCooldownInstances = Util.make(new ObjectArrayList<>(heldCooldownInstanceListTag.size()), (targetInstanceList) -> heldCooldownInstanceListTag.forEach((targetInstanceTag) -> targetInstanceList.add(SerializationUtil.deserializeCooldown((CompoundTag) targetInstanceTag))));
        ItemCooldowns curTracker = Minecraft.getInstance().player.getCooldowns();

        if (curTracker instanceof IItemCooldownsAccessor trackerAccessor) {
            Map<Item, ItemCooldowns.CooldownInstance> trackerCooldowns = trackerAccessor.getCooldowns();
            Map<Item, ItemCooldowns.CooldownInstance> newTrackerCooldowns = CollectionUtil.zipToMap(heldCooldownItems, heldCooldownInstances);

            trackerCooldowns.clear(); // Just in case :HEHEHEHA:
            trackerCooldowns.putAll(newTrackerCooldowns);
        }
    }
}
