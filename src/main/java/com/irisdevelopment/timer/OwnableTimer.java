package com.irisdevelopment.timer;

import org.bukkit.entity.Player;

/**
 * A basic ownable timer
 */
public interface OwnableTimer extends Timer {

	/**
	 * Gets the owning player
	 * @return The owning player
	 */
	Player getOwner();

}
