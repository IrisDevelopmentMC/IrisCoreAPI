package com.irisdevelopment.timer;

/**
 * A basic timer layout
 */
public interface Timer {

	/**
	 * Grabs the timer's ID
	 * @return The timers's ID
	 */
	String getID();

	/**
	 * Grabs the display name of the timer
	 * @return The dispaly name of the timer
	 */
	String getDisplayName();


	/**
	 * Grabs the timer's duration (milliseconds)
	 * @return The timer's duration (milliseconds)
	 */
	long getDuration();

	/**
	 * Grabs the timer's reminaing time (milliseconds)
	 * @return The timer's remaining time (milliseconds)
	 */
	long getRemaining();


	void setFinishedRunnable();

	Runnable getFinishedRunnable();


}
