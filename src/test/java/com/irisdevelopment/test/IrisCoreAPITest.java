package com.irisdevelopment.test;

import com.irisdevelopment.placeholder.PlaceholderAPI;
import com.irisdevelopment.test.placeholder.TestPlaceholderProvider;
import com.irisdevelopment.util.Format;

public class IrisCoreAPITest {

	public static void main(String[] args) {
		//TODO: Implement testing envirnment for IrisHCF

		System.out.println((testPlaceholderAPI()) ? "PlaceholderAPI passed" : "PlaceholderAPI failed");
		testTimeFormatting();
	}

	private static boolean testPlaceholderAPI() {
		try {
			PlaceholderAPI placeholderAPI = new PlaceholderAPI();

			placeholderAPI.registerProvider(new TestPlaceholderProvider());

			if (!placeholderAPI.formatPlaceholders("%test%").equals("true"))
				return false;

			placeholderAPI.reloadProviders();

			if (!placeholderAPI.formatPlaceholders("%test%").equals("true"))
				return false;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	private static void testTimeFormatting() {
		System.out.println(Format.millisecondTime(15226034L)); // 4:13:46
	}
}
