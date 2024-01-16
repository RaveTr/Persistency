package com.mememan.persistency.common.registrar;

import com.mememan.persistency.common.capabilities.base.ISerializableCapProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

import javax.annotation.Nullable;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.function.Consumer;
import java.util.function.Predicate;

public final class CapabilityRegistrar {
    private static final ConcurrentLinkedQueue<ISerializableCapProvider> QUEUED_CAP_PROVIDERS = new ConcurrentLinkedQueue<>();

    private CapabilityRegistrar() {

    }

    public static <ISCP extends ISerializableCapProvider> void registerCapabilityProvider(ISCP capabilityProvider) {
        QUEUED_CAP_PROVIDERS.add(capabilityProvider);
    }

    /**
     * Safely attempts to poll and consume all queued {@linkplain ISerializableCapProvider capability providers} in {@link #QUEUED_CAP_PROVIDERS}.
     *
     * @param registrarElementOperation The registrar operation to perform on each {@linkplain ISerializableCapProvider capability provider}, typically
     *                                  to attach them using {@link net.minecraftforge.event.AttachCapabilitiesEvent#addCapability(ResourceLocation, ICapabilityProvider)}.
     */
    public static void runAndEmptyRegistrar(Consumer<ISerializableCapProvider> registrarElementOperation) {
        runAndEmptyRegistrar(registrarElementOperation, null);
    }

    /**
     * Safely attempts to poll and consume all queued {@linkplain ISerializableCapProvider capability providers} in {@link #QUEUED_CAP_PROVIDERS} that meet
     * the criteria specified in the passed-in {@linkplain Predicate matchPredicate}.
     *
     * @param registrarElementOperation The registrar operation to perform on each accepted {@linkplain ISerializableCapProvider capability provider}, typically
     *                                  to attach them using {@link net.minecraftforge.event.AttachCapabilitiesEvent#addCapability(ResourceLocation, ICapabilityProvider)}.
     * @param matchPredicate            The operation {@link Predicate} each {@linkplain ISerializableCapProvider capability provider} in {@link #QUEUED_CAP_PROVIDERS} must meet
     *                                  in order to be polled. If {@code null}, all {@linkplain ISerializableCapProvider capability providers} will be polled.
     */
    public static void runAndEmptyRegistrar(Consumer<ISerializableCapProvider> registrarElementOperation, @Nullable Predicate<ISerializableCapProvider> matchPredicate) {
        if (QUEUED_CAP_PROVIDERS.isEmpty()) return;

        while (!QUEUED_CAP_PROVIDERS.isEmpty()) {
            ISerializableCapProvider curCap = QUEUED_CAP_PROVIDERS.poll();

            if (curCap == null || curCap.serializeNBT() == null || (matchPredicate != null && !matchPredicate.test(curCap))) continue;

            registrarElementOperation.accept(curCap);
        }
    }
}
