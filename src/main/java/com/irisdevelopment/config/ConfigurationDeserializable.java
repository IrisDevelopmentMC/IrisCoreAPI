package com.irisdevelopment.config;

import java.util.Map;

public interface ConfigurationDeserializable {

	void deserialize(Map<String, Object> map);

}
