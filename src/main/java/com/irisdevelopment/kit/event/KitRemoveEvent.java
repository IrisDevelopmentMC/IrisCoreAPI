package com.irisdevelopment.kit.event;

import com.irisdevelopment.kit.Kit;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Custom event for removing kits
 */
public class KitRemoveEvent extends Event implements Cancellable {

	private static final HandlerList handlers = new HandlerList();
	private @Getter final Kit kit;
	private @Getter @Setter boolean cancelled = false;

	public KitRemoveEvent(Kit kit) {
		this.kit = kit;
	}

	public HandlerList getHandlers() {
		return handlers;
	}

}
