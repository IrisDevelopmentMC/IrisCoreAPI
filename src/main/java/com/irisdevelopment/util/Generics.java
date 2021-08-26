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


	public static <E> ArrayList<E> typeFromList(List<?> list, Class<E> type) {
		ArrayList<E> typedList = new ArrayList<>();

		for (Object obj : list)
			if (type.isAssignableFrom(obj.getClass()))
				typedList.add(type.cast(obj));

		return typedList;
	}

	public static <E> HashSet<E> typeFromSet(HashSet<?> set, Class<E> type) {
		HashSet<E> typedSet = new HashSet<>();

		for (Object obj : set)
			if (type.isAssignableFrom(obj.getClass()))
				typedSet.add(type.cast(obj));

		return typedSet;
	}

	public static <K, V> HashMap<K, V> typeFromMap(HashMap<?, ?> map, Class<K> keyType, Class<V> valueType) {
		HashMap<K, V> typedMap = new HashMap<>();

		for (Map.Entry<?, ?> entry : map.entrySet())
			if (keyType.isAssignableFrom(entry.getKey().getClass()) && valueType.isAssignableFrom(entry.getValue().getClass()))
				typedMap.put(keyType.cast(entry.getKey()), valueType.cast(entry.getValue()));

		return typedMap;
	}
}
