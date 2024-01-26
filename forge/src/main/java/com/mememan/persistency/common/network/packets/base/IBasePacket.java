package com.mememan.persistency.common.network.packets.base;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public interface IBasePacket {

    /**
     * Called on the initial side to encode this packet instance's data before sending it to the target side.
     *
     * @param buf The {@linkplain FriendlyByteBuf} used to encode this packet instance's data.
     */
    void encode(FriendlyByteBuf buf);

    /**
     * Called on the target side, once this packet instance's data has successfully been decoded, to handle said data/run code at the time during which
     * this packet instance was sent/received.
     *
     * @param ctx The {@linkplain NetworkEvent.Context context} holding appropriate sided data, used to handle and mark this packet instance as handled.
     */
    void handle(Supplier<NetworkEvent.Context> ctx);
}
