package com.mememan.persistency.manager;

import com.mememan.persistency.PersistencyConstants;
import com.mememan.persistency.common.network.packets.s2c.SetCooldownCapabilityValuePacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

public class PersistencyNetworkManager {
    private static final String PROTOCOL_VERSION = "1";
    private static final SimpleChannel CHANNEL = NetworkRegistry.newSimpleChannel(PersistencyConstants.prefix("channel"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals);
    private static int PACKET_ID = 0;

    @SubscribeEvent
    static void registerPackets(FMLCommonSetupEvent event) {
        registerPackets();
    }

    private static void registerPackets() {
        registerCTSPackets();
        registerSTCPackets();
    }

    private static void registerCTSPackets() {
    }

    private static void registerSTCPackets() {
        CHANNEL.messageBuilder(SetCooldownCapabilityValuePacket.class, PACKET_ID++).encoder(SetCooldownCapabilityValuePacket::encode).decoder(SetCooldownCapabilityValuePacket::decode).consumerMainThread(SetCooldownCapabilityValuePacket::handle).add();
    }

    /**
     * Sends a packet from client to server. C2S.
     * @param packet Packet to send to the server.
     */
    public static void sendPacketToServer(Object packet) {
        CHANNEL.sendToServer(packet);
    }

    /**
     * Sends a packet to a player client from the server. S2C.
     * @param packet Packet to send to client.
     * @param targetPlayer The {@link ServerPlayer} (on the client) to send the specified packet object to.
     */
    public static void sendPacketToPlayer(Object packet, ServerPlayer targetPlayer) {
        CHANNEL.send(PacketDistributor.PLAYER.with(() -> targetPlayer), packet);
    }

    /**
     * Sends a packet to every connected client from the server. S2C.
     * @param packet Packet to send to clients.
     */
    public static void sendPacketToAll(Object packet) {
        CHANNEL.send(PacketDistributor.ALL.noArg(), packet);
    }

    /**
     * Sends a tracking entity/player packet to all tracking clients (as well as the specified {@link Entity}) from the server. S2C.
     * @param packet Packet to send (S2C).
     * @param trackedEntity The target tracked {@link Entity}.
     */
    public static void sendEntityTrackingPacket(Object packet, Player trackedEntity) {
        CHANNEL.send(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> trackedEntity), packet);
    }
}
