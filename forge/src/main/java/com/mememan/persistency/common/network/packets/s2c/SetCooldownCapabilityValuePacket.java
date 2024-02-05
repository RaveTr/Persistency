package com.mememan.persistency.common.network.packets.s2c;

import com.mememan.persistency.common.network.packets.base.IBasePacket;
import com.mememan.persistency.util.PacketUtil;
import it.unimi.dsi.fastutil.objects.Object2ObjectArrayMap;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemCooldowns;
import net.minecraftforge.network.NetworkEvent;

import java.util.Map;
import java.util.function.Supplier;

public class SetCooldownCapabilityValuePacket implements IBasePacket {
    private final Map<Item, ItemCooldowns.CooldownInstance> originalCooldowns;
    private final Map<Item, ItemCooldowns.CooldownInstance> newCooldowns;

    public SetCooldownCapabilityValuePacket(Map<Item, ItemCooldowns.CooldownInstance> originalCooldowns, Map<Item, ItemCooldowns.CooldownInstance> newCooldowns) {
        this.originalCooldowns = originalCooldowns;
        this.newCooldowns = newCooldowns;
    }

    public static SetCooldownCapabilityValuePacket decode(FriendlyByteBuf buf) {
        return new SetCooldownCapabilityValuePacket(buf.readMap(Object2ObjectArrayMap::new, (itemBuf) -> itemBuf.readItem().getItem(), PacketUtil::readCooldownInstance), buf.readMap(Object2ObjectArrayMap::new, (itemBuf) -> itemBuf.readItem().getItem(), PacketUtil::readCooldownInstance));
    }

    @Override
    public void encode(FriendlyByteBuf buf) {
        buf.writeMap(originalCooldowns, (itemBuf, targetItem) -> itemBuf.writeItem(targetItem.getDefaultInstance()), PacketUtil::writeCooldownInstance);
        buf.writeMap(newCooldowns, (itemBuf, targetItem) -> itemBuf.writeItem(targetItem.getDefaultInstance()), PacketUtil::writeCooldownInstance);
    }

    @Override
    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            originalCooldowns.clear();
            originalCooldowns.putAll(newCooldowns);
        });

        ctx.get().setPacketHandled(true);
    }
}
