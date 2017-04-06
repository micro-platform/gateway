package com.imta.microservices.eshop.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;

import java.util.List;

public class ConsulDiscovery {
    @Autowired
    private DiscoveryClient discoveryClient;

    public String test() {
        List<ServiceInstance> list = discoveryClient.getInstances("TEST");

        if (list != null && !list.isEmpty()) {
            return list.get(0).getUri().toString();
        }

        return null;
    }
}
