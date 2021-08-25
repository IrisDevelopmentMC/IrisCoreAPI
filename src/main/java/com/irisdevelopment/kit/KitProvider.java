package com.irisdevelopment.kit;

import java.util.List;
import java.util.UUID;

/**
 * A basic kit provider
 */
public interface KitProvider {

	/**
	 * Gets all kits available in the provider
	 * @return All kits in the provider
	 */
	List<Kit> getKits();

	/**
	 * Grabs kit with similar or the same name
	 * @param name The name to check
	 * @return Kit with same or similar name
	 */
	Kit getKit(String name);

	/**
	 * Grabs kit with the same uuid
	 * @param uuid The uuid to check
	 * @return Kit with the same uuid
	 */
	Kit getKit(UUID uuid);


	/**
	 * Adds, saves, and registers kit to the provider
	 * @param kit The kit to add
	 */
	void addKit(Kit kit);

	/**
	 * Removes and unregisters kit from the provider
	 * @param kit The kit to remove
	 */
	void removeKit(Kit kit);


	/**
	 * Reloads all kits saved in the provider
	 */
	void reloadKits();

	/**
	 * Saves all kits to the provider
	 */
	void saveKits();

	/**
	 * Saves provided kit to the provider
	 * @param kit The kit to save
	 */
	void saveKit(Kit kit);

}
