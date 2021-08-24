package com.irisdevelopment.placeholder;

import java.util.List;

/**
 * A provider interface for PlaceholderAPI.
 *
 * A list of placeholders should NOT be saved locally as the API will cache them when registered.
 * It is recommended you create a new List everytime the placeholders are grabbed.
 */
public interface PlaceholderProvider {

	/**
	 * Returns all placeholders from the provider
	 * @return A list of all placeholders
	 */
	List<Placeholder> getPlaceholders();


	/**
	 * Reloads all placeholders in the provider to be recached
	 */
	void reloadPlaceholders();

}
