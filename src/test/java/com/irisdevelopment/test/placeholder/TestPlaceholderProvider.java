package com.irisdevelopment.test.placeholder;

import com.irisdevelopment.placeholder.Placeholder;
import com.irisdevelopment.placeholder.PlaceholderProvider;

import java.util.ArrayList;
import java.util.List;

public class TestPlaceholderProvider implements PlaceholderProvider {


	@Override
	public List<Placeholder> getPlaceholders() {
		List<Placeholder> placeholders = new ArrayList<>();
		placeholders.add(Placeholder.create("test", "true"));
		return placeholders;
	}

	@Override
	public void reloadPlaceholders() {
		// Ignored
	}
}
