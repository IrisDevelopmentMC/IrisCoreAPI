package com.irisdevelopment.util;

import java.util.*;

/**
 * General use util for generic casting
 */
public class Generics {

	public static <E> ArrayList<E> castList(Object uncastedList, Class<E> type) {
		ArrayList<E> list = new ArrayList();

		if (uncastedList instanceof List) {

			List<?> castedList = (List)uncastedList;
			for (Object listObject : castedList)
				if (type.isAssignableFrom(listObject.getClass()))
					list.add(type.cast(listObject));

		}

		return list;
	}

	public static <E> HashSet<E> castSet(Object uncastedSet, Class<E> type) {
		HashSet<E> set = new HashSet();

		if (uncastedSet instanceof Set) {

			Set<?> castedSet = (Set)uncastedSet;
			for (Object listObject : castedSet)
				if (type.isAssignableFrom(listObject.getClass()))
					set.add(type.cast(listObject));

		}

		return set;
	}

	public static <K, V> HashMap<K, V> castMap(Object uncastedMap, Class<K> keyType, Class<V> valueType) {
		HashMap<K, V> map = new HashMap();

		if (uncastedMap instanceof Map) {

			Map<?, ?> castedMap = (Map)uncastedMap;
			for (Map.Entry<?, ?> entry : castedMap.entrySet())
				if (keyType.isAssignableFrom(entry.getKey().getClass()) && valueType.isAssignableFrom(entry.getValue().getClass()))
					map.put(keyType.cast(entry.getKey()), valueType.cast(entry.getValue()));

		}

		return map;
	}


	public static <E> E castObject(Object uncastedObj, Class<E> valueType) {
		return valueType.cast(uncastedObj);
	}

}
