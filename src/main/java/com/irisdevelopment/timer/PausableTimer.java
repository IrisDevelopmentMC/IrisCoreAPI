package com.irisdevelopment.timer;

/**
 * A basic pausable timer
 */
public interface PausableTimer extends Timer {

	/**
	 * Pauses timer
	 */
	void pause();

	/**
	 * Resumes timer
	 */
	void resume();

}
