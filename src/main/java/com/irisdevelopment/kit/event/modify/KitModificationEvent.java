package com.irisdevelopment.kit.event.modify;

import com.irisdevelopment.kit.Kit;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Custom event for modifying kits
 */
public class KitModificationEvent extends Event implements Cancellable {

	private static final HandlerList handlers = new HandlerList();
	private @Getter final Kit kit;
	private @Getter @Setter KitModification kitModification;
	private @Getter @Setter boolean cancelled = false;

	public KitModificationEvent(Kit kit, KitModification kitModification) {
		this.kit = kit;
	}

	public HandlerList getHandlers() {
		return handlers;
	}

}
