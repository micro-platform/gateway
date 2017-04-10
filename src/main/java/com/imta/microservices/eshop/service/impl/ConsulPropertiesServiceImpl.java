package com.imta.microservices.eshop.service.impl;

import com.ecwid.consul.v1.ConsulClient;
import com.ecwid.consul.v1.kv.model.GetValue;
import com.imta.microservices.eshop.service.ConsulPropertiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Properties;

@Service
public class ConsulPropertiesServiceImpl implements ConsulPropertiesService {
    @Autowired
    private DiscoveryClient discoveryClient;

	@Autowired
	private ConsulClient consulClient;

	@Value("${spring.application.name}")
	private String applicationName;

	private Properties yamlPropertiesFromKey;

	@Override
	public void loadPropertiesFromKey(String key) {
		List<ServiceInstance> list = discoveryClient.getInstances(applicationName);
		this.yamlPropertiesFromKey = new Properties();

		if (list != null && !list.isEmpty()) {
			ServiceInstance instance = list.get(0);

			GetValue valuesFromKey = consulClient.getKVValue(key).getValue();

			YamlPropertiesFactoryBean yaml = new YamlPropertiesFactoryBean();
			yaml.setResources(new ByteArrayResource(valuesFromKey.getDecodedValue().getBytes()));

			this.yamlPropertiesFromKey = yaml.getObject();
		}
	}

	@Override
	public String getYamlValue(String key) {
		return this.yamlPropertiesFromKey.getProperty(key);
	}

	@Override
	public String getYamlValue(String key, String defaultValue) {
		return this.yamlPropertiesFromKey.getProperty(key, defaultValue);
	}
}
