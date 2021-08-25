package com.irisdevelopment.kit.event.modify;

import lombok.Getter;
import org.bukkit.inventory.ItemStack;

import java.util.List;

/**
 * Enum for types of kit modifications
 */
public enum KitModificationType {

	NAME(String.class), DESCRIPTION(String.class), DISPLAY_ITEM(ItemStack.class),
	CONTENTS(List.class), ARMOR(List.class), POTION_EFFECTS(List.class),
	COOLDOWN(Long.class), MAXIMUM_USES(Integer.class);

	private final @Getter Class<?> valueType;

	KitModificationType(Class<?> valueType) {
		this.valueType = valueType;
	}

}
