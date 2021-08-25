package com.irisdevelopment.util;

import com.irisdevelopment.placeholder.PlaceholderAPI;
import lombok.Setter;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.util.List;

public class Chat {

	private static @Setter PlaceholderAPI placeholderAPI;

	public static String format(String str) {
		return
			ChatColor.translateAlternateColorCodes('&',
					placeholderAPI.formatPlaceholders(str));
	}

	public static void format(List<String> strs) {
		for (int i = 0; i < strs.size(); i++)
			strs.set(i,
					ChatColor.translateAlternateColorCodes('&',
							placeholderAPI.formatPlaceholders(strs.get(i))));
	}

	public static void send(String str, CommandSender user) {
		user.sendMessage(format(str));
	}

	public static void send(List<String> strs, CommandSender user) {
		format(strs);
		for (String str : strs)
			user.sendMessage(format(str));
	}

}
