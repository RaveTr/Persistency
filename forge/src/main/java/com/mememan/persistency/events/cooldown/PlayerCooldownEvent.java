package com.mememan.persistency.events.cooldown;

import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.Event;

/**
 * This event is fired whenever any of its child events are fired.
 * <br></br>
 * <br></br>
 * You can use each of these events to manipulate the {@linkplain Player#getCooldowns() cooldowns of players} directly, rather than just anonymously
 * (refer to {@link ItemCooldownEvent} for more information). As per the aforementioned event, you may cancel both {@link PlayerCooldownStartEvent} and
 * {@link PlayerCooldownTickEvent} to cancel the item cooldown(s) of a specific player. {@link PlayerCooldownEndEvent} is not cancelable, and should primarily be
 * utilised to detect that a specific cooldown has ended instead.
 * <br> </br>
 * <br> </br>
 * This event and its subclasses are all fired on the {@link MinecraftForge#EVENT_BUS}.
 */
public class PlayerCooldownEvent extends Event {
}
