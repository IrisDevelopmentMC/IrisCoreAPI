package com.irisdevelopment.placeholder;

import lombok.Getter;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * A fast and lightweight Placeholder API desgined for Iris plugins.
 */
public class PlaceholderAPI {

	// Not recommended to change this value at runtime or with conjunction with Iris plugins.
	// To update the Pattern cache, a provider reload is needed.
	public final char SEPERATOR = '%';

	// A cache of all registered placeholders
	private @Getter HashMap<Pattern, Placeholder> placeholderCache;

	// A cache of all registered placeholder providers
	private final @Getter HashSet<PlaceholderProvider> placeholderProviders;

	public PlaceholderAPI() {
		placeholderCache = new HashMap<>();
		placeholderProviders = new HashSet<>();
	}

	/**
	 * Registers, and caches, a placeholder provider
	 * @param placeholderProvider The placeholder provider
	 */
	public void registerProvider(PlaceholderProvider placeholderProvider) {
		// Makes sure there are no duplicates
		if (placeholderProviders.contains(placeholderProvider))
			return;

		// Saftey reload
		placeholderProvider.reloadPlaceholders();

		// Caches placeholders
		List<Placeholder> placeholders = placeholderProvider.getPlaceholders();
		for (Placeholder placeholder : placeholders)
			placeholderCache.put(Pattern.compile("(?:" + SEPERATOR + placeholder.getKey() + SEPERATOR + ")"), placeholder);

		// Caches provider
		placeholderProviders.add(placeholderProvider);
	}

	/**
	 * Reloads all providers in the cache and recaches all placeholders within them
	 */
	public void reloadProviders() {
		HashMap<Pattern, Placeholder> newCache = new HashMap<>();

		// Reloads provider then adds placeholders into new cache
		for (PlaceholderProvider placeholderProvider : placeholderProviders) {
			placeholderProvider.reloadPlaceholders();

			// Caches placeholders
			List<Placeholder> placeholders = placeholderProvider.getPlaceholders();
			for (Placeholder placeholder : placeholders)
				newCache.put(Pattern.compile("(?:" + SEPERATOR + placeholder.getKey() + SEPERATOR + ")"), placeholder);
		}

		placeholderCache = newCache;
	}


	/**
	 * Formats a provided string with any matching cached placeholders
	 * @param str The string to be formatted with placeholders
	 * @return The formatted string
	 */
	public String formatPlaceholders(String str) {
		// Iterates over the entries and uses Pattern matcher to replace any placeholders
		for (Map.Entry<Pattern, Placeholder> entry : placeholderCache.entrySet())
			str = entry.getKey().matcher(str).replaceAll(entry.getValue().getValue());

		return str;
	}

}
