package com.irisdevelopment.util;

import com.irisdevelopment.placeholder.PlaceholderAPI;
import com.irisdevelopment.config.Configuration;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.util.List;

public class Chat {

	private static @Setter PlaceholderAPI placeholderAPI;
	private static @Setter Configuration messageConfiguration;

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

	public static void sendFromConfig(String configKey, CommandSender user) {
		if (!messageConfiguration.contains(configKey))
			return;

		if (messageConfiguration.isString(configKey))
			user.sendMessage(format(messageConfiguration.getString(configKey)));
		else {
			List<String> strs = messageConfiguration.getStringList(configKey);
			format(strs);
			for (String str : strs)
				user.sendMessage(format(str));
		}
	}

	public static void broadcast(String str) {
		Bukkit.broadcastMessage(format(str));
	}

	public static void broadcast(List<String> strs) {
		format(strs);
		for (String str : strs)
			Bukkit.broadcastMessage(format(str));
	}

	public static void broadcastFromConfig(String configKey) {
		if (!messageConfiguration.contains(configKey))
			return;

		if (messageConfiguration.isString(configKey))
			Bukkit.broadcastMessage(format(messageConfiguration.getString(configKey)));
		else {
			List<String> strs = messageConfiguration.getStringList(configKey);
			format(strs);
			for (String str : strs)
				Bukkit.broadcastMessage(format(str));
		}
	}


}
