package com.irisdevelopment.config;

import lombok.Getter;
import org.bukkit.Color;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

import java.io.*;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Provides more funcationality to the FileConfiguration class
 */
public class Configuration implements ConfigurationSection {

	public final static HashSet<Configuration> staticReloadableConfigurations = new HashSet<>();

	private @Getter FileConfiguration fileConfig;
	private final @Getter File folder;
	private final @Getter String configName;

	private final InputStream configStream;
	private final JavaPlugin plugin;


	public Configuration(JavaPlugin plugin, String configName, boolean isStaticReloadable) {
		this(plugin, plugin.getDataFolder(), configName, plugin.getResource(configName), isStaticReloadable);
	}

	public Configuration(JavaPlugin plugin, File folder, String configName, boolean isStaticReloadable) {
		this(plugin, folder, configName, plugin.getResource(configName), isStaticReloadable);
	}

	public Configuration(JavaPlugin plugin, String configName, InputStream configStream, boolean isStaticReloadable) {
		this(plugin, plugin.getDataFolder(), configName, configStream, isStaticReloadable);
	}

	public Configuration(JavaPlugin plugin, File folder, String configName, InputStream configStream, boolean isStaticReloadable) {
		this.plugin = plugin;
		this.folder = folder;
		this.configName = configName;
		this.configStream = configStream;

		if (isStaticReloadable)
			staticReloadableConfigurations.add(this);
	}


	public void reload() throws UnsupportedEncodingException {
		File file = new File(folder, configName);
		if (!file.exists())
			plugin.saveResource(configName, false);

		fileConfig = YamlConfiguration.loadConfiguration(file);

		Reader defConfigStream = new InputStreamReader(configStream, "UTF8");

		YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
		fileConfig.setDefaults(defConfig);

		fileConfig.options().copyDefaults(true);
	}

	public void save() throws IOException {
		File file = new File(folder, configName);
		fileConfig.save(file);
	}

	/*
	 * Wrappers
	 */

	@Override
	public Set<String> getKeys(boolean bool) {
		return fileConfig.getKeys(bool);
	}

	@Override
	public Map<String, Object> getValues(boolean bool) {
		return fileConfig.getValues(bool);
	}

	@Override
	public boolean contains(String key) {
		return fileConfig.contains(key);
	}

	@Override
	public boolean isSet(String key) {
		return fileConfig.isSet(key);
	}

	@Override
	public String getCurrentPath() {
		return fileConfig.getCurrentPath();
	}

	@Override
	public String getName() {
		return fileConfig.getName();
	}

	@Override
	public org.bukkit.configuration.Configuration getRoot() {
		return fileConfig.getRoot();
	}

	@Override
	public ConfigurationSection getParent() {
		return fileConfig.getParent();
	}

	@Override
	public Object get(String key) {
		return fileConfig.get(key);
	}

	@Override
	public Object get(String key, Object obj) {
		return fileConfig.get(key, obj);
	}

	@Override
	public void set(String key, Object obj) {
		fileConfig.set(key, obj);
	}

	@Override
	public ConfigurationSection createSection(String key) {
		return fileConfig.createSection(key);
	}

	@Override
	public ConfigurationSection createSection(String key, Map<?, ?> map) {
		return fileConfig.createSection(key, map);
	}

	@Override
	public String getString(String key) {
		return fileConfig.getString(key);
	}

	@Override
	public String getString(String key, String def) {
		return fileConfig.getString(key, def);
	}

	@Override
	public boolean isString(String key) {
		return fileConfig.isString(key);
	}

	@Override
	public int getInt(String key) {
		return fileConfig.getInt(key);
	}

	@Override
	public int getInt(String key, int def) {
		return fileConfig.getInt(key, def);
	}

	@Override
	public boolean isInt(String key) {
		return fileConfig.isInt(key);
	}

	@Override
	public boolean getBoolean(String key) {
		return fileConfig.getBoolean(key);
	}

	@Override
	public boolean getBoolean(String key, boolean def) {
		return fileConfig.getBoolean(key, def);
	}

	@Override
	public boolean isBoolean(String key) {
		return fileConfig.isBoolean(key);
	}

	@Override
	public double getDouble(String key) {
		return fileConfig.getDouble(key);
	}

	@Override
	public double getDouble(String key, double def) {
		return fileConfig.getDouble(key, def);
	}

	@Override
	public boolean isDouble(String key) {
		return fileConfig.isDouble(key);
	}

	@Override
	public long getLong(String key) {
		return fileConfig.getLong(key);
	}

	@Override
	public long getLong(String key, long def) {
		return fileConfig.getLong(key, def);
	}

	@Override
	public boolean isLong(String key) {
		return fileConfig.isLong(key);
	}

	@Override
	public List<?> getList(String key) {
		return fileConfig.getList(key);
	}

	@Override
	public List<?> getList(String key, List<?> def) {
		return fileConfig.getList(key, def);
	}

	@Override
	public boolean isList(String key) {
		return fileConfig.isList(key);
	}

	@Override
	public List<String> getStringList(String key) {
		return fileConfig.getStringList(key);
	}

	@Override
	public List<Integer> getIntegerList(String key) {
		return fileConfig.getIntegerList(key);
	}

	@Override
	public List<Boolean> getBooleanList(String key) {
		return fileConfig.getBooleanList(key);
	}

	@Override
	public List<Double> getDoubleList(String key) {
		return fileConfig.getDoubleList(key);
	}

	@Override
	public List<Float> getFloatList(String key) {
		return fileConfig.getFloatList(key);
	}

	@Override
	public List<Long> getLongList(String key) {
		return fileConfig.getLongList(key);
	}

	@Override
	public List<Byte> getByteList(String key) {
		return fileConfig.getByteList(key);
	}

	@Override
	public List<Character> getCharacterList(String key) {
		return fileConfig.getCharacterList(key);
	}

	@Override
	public List<Short> getShortList(String key) {
		return fileConfig.getShortList(key);
	}

	@Override
	public List<Map<?, ?>> getMapList(String key) {
		return fileConfig.getMapList(key);
	}

	@Override
	public Vector getVector(String key) {
		return fileConfig.getVector(key);
	}

	@Override
	public Vector getVector(String key, Vector def) {
		return fileConfig.getVector(key, def);
	}

	@Override
	public boolean isVector(String key) {
		return fileConfig.isVector(key);
	}

	@Override
	public OfflinePlayer getOfflinePlayer(String key) {
		return fileConfig.getOfflinePlayer(key);
	}

	@Override
	public OfflinePlayer getOfflinePlayer(String key, OfflinePlayer def) {
		return fileConfig.getOfflinePlayer(key, def);
	}

	@Override
	public boolean isOfflinePlayer(String key) {
		return fileConfig.isOfflinePlayer(key);
	}

	@Override
	public ItemStack getItemStack(String key) {
		return fileConfig.getItemStack(key);
	}

	@Override
	public ItemStack getItemStack(String key, ItemStack def) {
		return fileConfig.getItemStack(key, def);
	}

	@Override
	public boolean isItemStack(String key) {
		return fileConfig.isItemStack(key);
	}

	@Override
	public Color getColor(String key) {
		return fileConfig.getColor(key);
	}

	@Override
	public Color getColor(String key, Color def) {
		return fileConfig.getColor(key, def);
	}

	@Override
	public boolean isColor(String key) {
		return fileConfig.isColor(key);
	}

	@Override
	public ConfigurationSection getConfigurationSection(String key) {
		return fileConfig.getConfigurationSection(key);
	}

	@Override
	public boolean isConfigurationSection(String key) {
		return fileConfig.isConfigurationSection(key);
	}

	@Override
	public ConfigurationSection getDefaultSection() {
		return fileConfig.getDefaultSection();
	}

	@Override
	public void addDefault(String key, Object obj) {
		fileConfig.addDefault(key, obj);
	}

	public void deserializeFromSection(ConfigurationSection section, ConfigurationDeserializable deserializable) {
		deserializable.deserialize(section.getValues(false));
	}

}
