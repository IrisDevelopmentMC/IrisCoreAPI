package com.irisdevelopment.kit.impl;

import com.irisdevelopment.config.Configuration;
import com.irisdevelopment.kit.Kit;
import com.irisdevelopment.kit.KitProvider;
import com.irisdevelopment.kit.event.KitCreateEvent;
import com.irisdevelopment.kit.event.KitRemoveEvent;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ConfigKitManager implements KitProvider {

	private final @Getter Configuration configuration;

	private @Getter List<Kit> kits;

	public ConfigKitManager(Configuration configuration) {
		this.configuration = configuration;
		reloadKits();
	}


	@Override
	public Kit getKit(String name) {
		for (Kit kit : kits)
			if (kit.getName().toLowerCase().contains(name.toLowerCase()))
				return kit;
		return null;
	}

	@Override
	public Kit getKit(UUID uuid) {
		for (Kit kit : kits)
			if (kit.getUuid().equals(uuid))
				return kit;
		return null;
	}

	@Override
	public void addKit(Kit kit) {
		KitCreateEvent event = new KitCreateEvent(kit);
		Bukkit.getPluginManager().callEvent(event);

		if (event.isCancelled())
			return;

		kits.add(kit);
	}

	@Override
	public void removeKit(Kit kit) {
		KitRemoveEvent event = new KitRemoveEvent(kit);
		Bukkit.getPluginManager().callEvent(event);

		if (event.isCancelled())
			return;

		kits.remove(kit);
		configuration.set(kit.getUuid().toString(), null);
	}


	@Override
	public void reloadKits() {
		try {
			configuration.reload();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		kits = new ArrayList<>();
		for (String key : configuration.getKeys(false)) {
			ConfigurationSection section = configuration.getConfigurationSection(key);

			Kit kit = new Kit();
			kit.deserialize(section.getValues(false));

			kits.add(kit);
		}
	}

	@Override
	public void saveKits() {
		for (Kit kit : kits)
			saveKit(kit);
	}

	@Override
	public void saveKit(Kit kit) {
		ConfigurationSection section = configuration.createSection(kit.getUuid().toString(), kit.serialize());
		configuration.set(kit.getUuid().toString(), section);
		try {
			configuration.save();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
