package com.irisdevelopment.kit;

import com.irisdevelopment.util.Generics;
import com.irisdevelopment.config.ConfigurationDeserializable;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Material;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

import java.util.*;


/**
 * A basic Kit object that can be serialized and deserialized
 */
public class Kit implements ConfigurationSerializable, ConfigurationDeserializable {

	private @Getter @Setter UUID uuid = UUID.randomUUID();
	private @Getter @Setter String name;
	private @Getter @Setter String description = "N/A";
	private @Getter @Setter ItemStack displayItem = new ItemStack(Material.LEATHER_HELMET);

	private @Getter @Setter ItemStack[] contents;
	private @Getter @Setter ItemStack[] armor;
	private @Getter @Setter Collection<PotionEffect> potionEffects = new ArrayList<>();

	private @Getter @Setter long cooldown = 0L;
	private @Getter @Setter int maximumUses = Integer.MAX_VALUE;


	/**
	 * Creates a generic kit
	 * @param name The name of the Kit
	 * @param contents The kit's contents
	 * @param armor The kit's armor contents
	 */
	public Kit(String name, ItemStack[] contents, ItemStack[] armor) {
		this.name = name;
		this.contents = contents;
		this.armor = armor;
	}

	/**
	 * Creats an empty Kit, used for deserializing
	 */
	public Kit() {
		this.name = null;
		this.contents = null;
		this.armor = null;
	}

	/**
	 * Serializes the kit for a config
	 * @return The serialized kit map
	 */
	@Override
	public Map<String, Object> serialize() {
		Map<String, Object> serializedKit = new LinkedHashMap<>();

		serializedKit.put("uuid", uuid.toString());
		serializedKit.put("name", name);
		serializedKit.put("description", description);
		serializedKit.put("displayItem", displayItem);

		serializedKit.put("contents", contents);
		serializedKit.put("armor", armor);
		serializedKit.put("potionEffects", potionEffects);

		serializedKit.put("cooldown", cooldown);
		serializedKit.put("maximumUses", maximumUses);

		return serializedKit;
	}

	/**
	 * Deserializes a kit map
	 * @param map The serialized kit
	 */
	@Override
	public void deserialize(Map<String, Object> map) {
		this.setUuid(UUID.fromString((String)map.get("uuid")));
		this.setName((String)map.get("name"));
		this.setDescription((String)map.get("description"));
		this.setDisplayItem((ItemStack) map.get("displayItem"));

		List<ItemStack> contents = Generics.castList(map.get("contents"), ItemStack.class);
		this.setContents(contents.toArray(new ItemStack[contents.size()]));

		List<ItemStack> armor = Generics.castList(map.get("armor"), ItemStack.class);
		this.setArmor(armor.toArray(new ItemStack[armor.size()]));

		this.setPotionEffects(Generics.castList(map.get("potionEffects"), PotionEffect.class));

		this.setCooldown(Long.parseLong((String)map.get("cooldown")));
		this.setMaximumUses((Integer) map.get("maximumUses"));
	}
}
