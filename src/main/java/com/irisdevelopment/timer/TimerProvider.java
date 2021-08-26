package com.irisdevelopment.timer;


import java.util.HashSet;

/**
 * A basic timer provider
 */
public interface TimerProvider {

	/**
	 * Grabs the providers timers
	 * @return A hashset of the timers
	 */
	HashSet<Timer> getTimers();

	/**
	 * Starts a timer with the provider
	 * @param timer The timer
	 */
	void startTimer(Timer timer);

	/**
	 * Stops/cancels a timer with the provider
	 * @param timer The timer
	 */
	void cancelTimer(Timer timer);

}
