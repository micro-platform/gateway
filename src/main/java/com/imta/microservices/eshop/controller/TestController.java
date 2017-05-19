package com.imta.microservices.eshop.controller;

import com.imta.microservices.eshop.service.ConsulPropertiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
public class TestController {
    @Autowired
    private ConsulPropertiesService consulPropertiesService;

    @RequestMapping("/test")
    public String test() {
        return "Test qui marche";
    }

    @RequestMapping("/testDiscovery")
    public String getListInstance(HttpServletResponse response, @RequestParam("instanceName") String instanceName, @RequestParam("property") String property, @RequestParam("key") String key) {
        response.setHeader("Content-Type", "text/html; charset=utf-8");

        consulPropertiesService.loadPropertiesFromKey(key);

        return consulPropertiesService.getYamlValue(property, "NONE");
    }
}
