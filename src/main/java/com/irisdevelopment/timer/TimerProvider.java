package com.irisdevelopment.timer;

import java.util.HashMap;

/**
 * A basic timer provider
 */
public interface TimerProvider {

	/**
	 * Grabs the providers timers
	 * @return A hashed map of timer ids and timers
	 */
	HashMap<String, Timer> getTimerMap();

	/**
	 * Grabs the active timers' ids and their start time
	 * @return The active timer start time map
	 */
	HashMap<String, Long> getActiveTimers();


	/**
	 * Registers a timer with the provider
	 * @param timer The timer to register
	 */
	void registerTimer(Timer timer);

	/**
	 * Unregisters the timer from the provider
	 */
	void unregisterTimer(Timer timer);


	/**
	 * Starts a registered timer
	 * @param timer The registered timer
	 */
	void startTimer(Timer timer);

	/**
	 * Stops/cancels an active registerd timer
	 * @param timer The active registered timer
	 */
	void cancelTimer(Timer timer);

}
