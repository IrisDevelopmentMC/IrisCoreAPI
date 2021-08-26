package com.irisdevelopment.timer.event;

import com.irisdevelopment.timer.Timer;
import lombok.Getter;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class TimerFinishEvent extends Event {

	private static final HandlerList handlers = new HandlerList();
	private @Getter final Timer timer;

	public TimerFinishEvent(Timer timer) {
		this.timer = timer;
	}

	public HandlerList getHandlers() {
		return handlers;
	}

}
