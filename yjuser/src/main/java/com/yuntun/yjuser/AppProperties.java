package com.yuntun.yjuser;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("app")
public class AppProperties {
	private final Map<String, String> url = new HashMap<>();

	public Map<String, String> getUrl() {
		return url;
	}
}
