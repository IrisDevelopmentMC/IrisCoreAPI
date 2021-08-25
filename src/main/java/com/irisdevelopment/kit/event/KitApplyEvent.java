package com.irisdevelopment.kit.event;

import com.irisdevelopment.kit.Kit;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;

/**
 * Custom event for applying kits
 */
public class KitApplyEvent extends PlayerEvent implements Cancellable {

	private static final HandlerList handlers = new HandlerList();
	private @Getter final Kit kit;
	private @Getter @Setter boolean cancelled = false;

	public KitApplyEvent(Kit kit, Player player) {
		super(player);
		this.kit = kit;
	}

	public HandlerList getHandlers() {
		return handlers;
	}

}
