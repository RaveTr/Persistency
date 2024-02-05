package com.mememan.persistency.util;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.item.ItemCooldowns;

public final class PacketUtil {

    private PacketUtil() {
        throw new IllegalAccessError("Attempted to construct a Utility Class!");
    }

    /**
     * Encodes a {@link ItemCooldowns.CooldownInstance} into the specified {@link FriendlyByteBuf}. For reading the value on the target side,
     * see {@link #readCooldownInstance(FriendlyByteBuf)}. Note that this encoding is handled sequentially, meaning that the reader indexes of both the
     * start and end ticks of this {@link ItemCooldowns.CooldownInstance} should be retrieved sequentially, too (basically just use {@link #readCooldownInstance(FriendlyByteBuf)}).
     *
     * @param buf The target {@link FriendlyByteBuf} in which the specified {@link ItemCooldowns.CooldownInstance} should be encoded.
     * @param targetCooldownInstance The {@link ItemCooldowns.CooldownInstance} to encode.
     */
    public static void writeCooldownInstance(FriendlyByteBuf buf, ItemCooldowns.CooldownInstance targetCooldownInstance) {
        buf.writeInt(targetCooldownInstance.startTime);
        buf.writeInt(targetCooldownInstance.endTime);
    }

    /**
     * Returns a new {@link ItemCooldowns.CooldownInstance} decoded from the specified {@link FriendlyByteBuf}. For encoding the value on the initial side,
     * see {@link #writeCooldownInstance(FriendlyByteBuf, ItemCooldowns.CooldownInstance)}. Note that this method should be called at the appropriate reader
     * index under the appropriate context, as it's merely a shortcut method. It does NOT modify the reader index of the specified {@link FriendlyByteBuf} to
     * find any specific cooldown values (duh).
     * <br></br>
     * <br></br>
     * <b>Note:</b> The {@link ItemCooldowns.CooldownInstance} returned is a newly instantiated object sharing the same data value as the one initially encoded.
     * As such, which it does suffice for most use-cases (duh), it should NOT be used for equality checks, as the object itself is different. If you have to do an
     * equality check, do it based on the stored cooldown data (start/end ticks), not the object itself!
     *
     * @param buf The source {@link FriendlyByteBuf} from which the {@link ItemCooldowns.CooldownInstance} should be decoded.
     *
     * @return A newly instantiated {@link ItemCooldowns.CooldownInstance}, holding the same cooldown data as the initially encoded {@link ItemCooldowns.CooldownInstance},
     * decoded from the specified {@link FriendlyByteBuf}.
     */
    public static ItemCooldowns.CooldownInstance readCooldownInstance(FriendlyByteBuf buf) {
        int start = buf.readInt();
        int end = buf.readInt();

        return new ItemCooldowns.CooldownInstance(start, end);
    }
}
