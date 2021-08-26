package com.irisdevelopment.util;

import java.util.concurrent.TimeUnit;

public class Format {

	public static String enumName(Enum<?> e) {
		String rawName = e.name();

		String[] words = rawName.split("_");

		for (int i = 0; i < words.length; i++)
			words[i] = words[i].substring(0, 1).toUpperCase() + words[i].substring(1).toLowerCase();

		return String.join(" ", words);
	}

	public static String millisecondTime(long duration) {
		// Greater than or equal to an hour
		if (duration >= 3600000L) { // HH:mm:ss
			return String.format("%d:%d:%d", TimeUnit.MILLISECONDS.toHours(duration),
					TimeUnit.MILLISECONDS.toMinutes(duration) % TimeUnit.HOURS.toMinutes(1),
					TimeUnit.MILLISECONDS.toSeconds(duration) % TimeUnit.MINUTES.toSeconds(1));
		}
		// Greater than or equal to a minute
		else if (duration >= 60000L) { // mm:ss
			return String.format("%d:%d", TimeUnit.MILLISECONDS.toMinutes(duration),
					TimeUnit.MILLISECONDS.toSeconds(duration) % TimeUnit.MINUTES.toSeconds(1));
		}
		// Greater than or equal to 10 seconds
		else if (duration >= 10000L) { // ss
			return String.format("%d", TimeUnit.MILLISECONDS.toSeconds(duration));
		}
		// Less than 10 seconds
		else { // ss.MM
			return String.format("%d.%d", TimeUnit.MILLISECONDS.toSeconds(duration),
					duration % TimeUnit.SECONDS.toMillis(1)).substring(0, 3);
		}
	}
}
