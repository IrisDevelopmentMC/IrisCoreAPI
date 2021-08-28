package com.irisdevelopment.timer.impl;

import com.irisdevelopment.timer.Timer;
import com.irisdevelopment.timer.TimerProvider;
import com.irisdevelopment.timer.event.TimerCancelEvent;
import com.irisdevelopment.timer.event.TimerFinishEvent;
import com.irisdevelopment.timer.event.TimerStartEvent;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashSet;

/**
 * A basic timer provider using a sync bukkit task for updating
 */
public class BasicTimerManager implements TimerProvider {

	private final JavaPlugin plugin;

	private @Getter HashSet<Timer> timers;
	private @Getter int taskID;
	private final @Getter long updateInterval;

	/**
	 * Creates a basic timer provider with a set updateInterval for the sync bukkit task
	 * @param plugin The plugin's instance
	 * @param updateInterval The update interval for the bukkit task (ticks)
	 */
	public BasicTimerManager(JavaPlugin plugin, long updateInterval) {
		this.plugin = plugin;
		this.updateInterval = updateInterval;

		this.timers = new HashSet<>();

		createTask();
	}

	private void createTask() {
		taskID = plugin.getServer().getScheduler().scheduleSyncRepeatingTask(plugin,
			() -> {
				for (Timer timer : timers)
					if (timer.getRemaining() <= 0L) {
						TimerFinishEvent event = new TimerFinishEvent(timer);
						Bukkit.getPluginManager().callEvent(event);

						timers.remove(timer);
						timer.onCompletion();
					}
			}, updateInterval, updateInterval);
	}

	@Override
	public void startTimer(Timer timer) {
		TimerStartEvent event = new TimerStartEvent(timer);
		Bukkit.getPluginManager().callEvent(event);

		if (event.isCancelled())
			return;

		timers.add(timer);
		timer.onStart();
	}

	@Override
	public void cancelTimer(Timer timer) {
		TimerCancelEvent event = new TimerCancelEvent(timer);
		Bukkit.getPluginManager().callEvent(event);

		if (event.isCancelled())
			return;

		timers.remove(timer);
		timer.onCancelation();
	}
}
