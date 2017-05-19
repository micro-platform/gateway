package com.imta.microservices.eshop.service;
/*
TODO: add methods to get properties from FILE format and Properties format
*/
public interface ConsulPropertiesService {
	/**
	 * Load all properties corresponding to a consul key
	 *
	 * @param key the consul key
	 */
	void loadPropertiesFromKey(String key);

	String getYamlValue(String key);
	String getYamlValue(String key, String defaultValue);
}
