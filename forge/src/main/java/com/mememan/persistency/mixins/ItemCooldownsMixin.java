package com.mememan.persistency.mixins;

import com.mememan.persistency.events.PersistencyEventFactory;
import com.mememan.persistency.events.cooldown.ItemCooldownStartEvent;
import com.mememan.persistency.events.cooldown.ItemCooldownTickEvent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemCooldowns;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;

@Mixin(ItemCooldowns.class)
public abstract class ItemCooldownsMixin {
    @Shadow
    @Final
    @Mutable
    private Map<Item, ItemCooldowns.CooldownInstance> cooldowns;
    @Shadow
    private int tickCount;

    private ItemCooldownsMixin() {
        throw new IllegalAccessError("Attempted to construct a Mixin Class!");
    }

    @Inject(method = "Lnet/minecraft/world/item/ItemCooldowns;onCooldownStarted(Lnet/minecraft/world/item/Item;I)V", at = @At("HEAD"), cancellable = true)
    private void persistency$onCooldownStarted(Item targetCooldownItem, int cooldownTicks, CallbackInfo cir) {
        ItemCooldowns curTracker = (ItemCooldowns) (Object) this;
        ItemCooldownStartEvent icdsEvent = PersistencyEventFactory.onItemCooldownStart(targetCooldownItem, cooldownTicks, false);
        int eventCooldownTicks = icdsEvent.getCooldownTicks();
        boolean shouldHardsetCooldownTicks = icdsEvent.isHardSetCooldownTicks();

        if (icdsEvent.isCanceled()) {
            if (cooldowns.containsKey(targetCooldownItem)) cooldowns.remove(targetCooldownItem);
            cir.cancel();
        }

        if (cooldownTicks != eventCooldownTicks) {
            cooldowns.computeIfPresent(targetCooldownItem, (cooldownItem, itemCooldown) -> {
                int cooldownDelta = eventCooldownTicks > itemCooldown.endTime ? eventCooldownTicks - itemCooldown.endTime : itemCooldown.endTime - eventCooldownTicks;
                ItemCooldowns.CooldownInstance updatedItemCooldown = new ItemCooldowns.CooldownInstance(tickCount, shouldHardsetCooldownTicks ? tickCount + eventCooldownTicks : itemCooldown.endTime + cooldownDelta);
                return updatedItemCooldown;
            });
        }
    }

    @Inject(method = "Lnet/minecraft/world/item/ItemCooldowns;tick()V", at = @At("TAIL"), cancellable = true)
    private void persistency$tick(CallbackInfo cir) {
        ItemCooldowns curTracker = (ItemCooldowns) (Object) this;

        cooldowns.forEach((targetCooldownItem, targetItemCooldown) -> {
            ItemCooldownTickEvent icdtEvent = PersistencyEventFactory.onItemCooldownTick(targetCooldownItem, targetItemCooldown.endTime, false);
            int eventCooldownTicks = icdtEvent.getCooldownTicks();
            boolean shouldHardsetCooldownTicks = icdtEvent.isHardSetCooldownTicks();

            if (icdtEvent.isCanceled()) {
                if (cooldowns.containsKey(targetCooldownItem)) cooldowns.remove(targetCooldownItem);
                cir.cancel();
            }

            if (targetItemCooldown.endTime != icdtEvent.getCooldownTicks()) {
                cooldowns.computeIfPresent(targetCooldownItem, (cooldownItem, itemCooldown) -> {
                    int cooldownDelta = eventCooldownTicks > itemCooldown.endTime ? eventCooldownTicks - itemCooldown.endTime : itemCooldown.endTime - eventCooldownTicks;
                    ItemCooldowns.CooldownInstance updatedItemCooldown = new ItemCooldowns.CooldownInstance(tickCount, shouldHardsetCooldownTicks ? tickCount + eventCooldownTicks : itemCooldown.endTime + cooldownDelta);
                    return updatedItemCooldown;
                });
            }
        });
    }

    @Inject(method = "Lnet/minecraft/world/item/ItemCooldowns;onCooldownEnded(Lnet/minecraft/world/item/Item;)V", at = @At("HEAD"), cancellable = true)
    private void chaosawakens$onCooldownEnded(Item targetCooldownItem, CallbackInfo cir) {
        PersistencyEventFactory.onItemCooldownEnd(targetCooldownItem);
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
