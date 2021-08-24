package com.irisdevelopment.placeholder;

/**
 * An abstract Placeholder for the PlaceholderAPI.
 * For static Placeholders, use the static create method.
 * For dynamic Placeholder values, implement this abstract class.
 */
public abstract class Placeholder {

	/**
	 * Creates a placeholder with given key and value
	 * @param key The placeholder's key
	 * @param value The placeholder's value
	 * @return A static placeholder
	 */
	public static Placeholder create(String key, String value) {
		return new Placeholder() {
			@Override
			public String getKey() {
				return key;
			}

			@Override
			public String getValue() {
				return value;
			}
		};
	}

	/**
	 * Grab the placeholder's key
	 * @return A static key
	 */
	public abstract String getKey();

	/**
	 * Grab the placeholder's value
	 * @return A static or dynamic value
	 */
	public abstract String getValue();

}
