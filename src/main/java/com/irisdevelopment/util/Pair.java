package com.irisdevelopment.util;

import lombok.Getter;

/**
 * Generic Pair object
 * @param <K> Key type
 * @param <V> Value type
 */
public class Pair<K, V> {

	private final @Getter K key;
	private final @Getter V value;

	public Pair(K key, V value) {
		this.key = key;
		this.value = value;
	}

}
