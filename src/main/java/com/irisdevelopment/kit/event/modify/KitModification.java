package com.irisdevelopment.kit.event.modify;

import lombok.Getter;
import lombok.Setter;

/**
 * Object that stores the type of kit modification and its value
 */
public class KitModification {

	private final @Getter KitModificationType modificationType;
	private @Getter @Setter Object value;

	public KitModification(KitModificationType modificationType, Object value) {
		this.modificationType = modificationType;
		this.value = value;
	}

}
