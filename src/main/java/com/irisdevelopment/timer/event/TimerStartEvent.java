package com.irisdevelopment.timer.event;

import com.irisdevelopment.timer.Timer;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class TimerStartEvent extends Event implements Cancellable {

	private static final HandlerList handlers = new HandlerList();
	private @Getter final Timer timer;
	private @Getter @Setter boolean cancelled = false;

	public TimerStartEvent(Timer timer) {
		this.timer = timer;
	}

	public HandlerList getHandlers() {
		return handlers;
	}

}
