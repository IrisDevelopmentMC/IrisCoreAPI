package com.irisdevelopment.scoreboard;

import lombok.Getter;

import java.util.Arrays;

public class ScoreboardEntry {

	private final @Getter String name;
	private final @Getter String prefix;
	private final @Getter String suffix;

	public ScoreboardEntry(String name) {
		this("", name , "");
	}

	public ScoreboardEntry(String prefix, String name, String suffix) {
		if (prefix.length() > 16)
			prefix = prefix.substring(0, 16);
		if (name.length() > 16)
			name = name.substring(0, 16);
		if (suffix.length() > 16)
			suffix = suffix.substring(0, 16);

		this.prefix = prefix;
		this.name = name;
		this.suffix = suffix;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof ScoreboardEntry))
			return false;

		ScoreboardEntry other = (ScoreboardEntry) object;

		return
				other.getPrefix().equals(getPrefix()) &&
				other.getName().equals(getName()) &&
				other.getSuffix().equals(getSuffix());
	}

	/**
	 * Splits a string evenly into a prefix, name, and suffix to gain a longer board entry
	 * Max length, for this to properly work, is 48
	 * Anything longer will be cut off
	 *
	 * Since this is an auto solution, duplicate names can occur
	 * should only be used with known input
	 * @param str The string to split
	 * @return The entry
	 */
	public static ScoreboardEntry splitToEntry(String str) {
		if (str.length() <= 16)
			return new ScoreboardEntry(str);

		if (str.length() > 48)
			str = str.substring(0, 48);

		String[] sections = new String[3];
		Arrays.fill(sections, "");

		for (int i = 0; i < Math.ceil(str.length() / 16.0); i++)
			sections[i] = str.substring(i*16, Math.min((i + 1) * 16, str.length()));

		return new ScoreboardEntry(sections[0], sections[1], sections[2]);
	}

}
