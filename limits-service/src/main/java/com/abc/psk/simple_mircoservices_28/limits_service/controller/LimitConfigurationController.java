package com.abc.psk.simple_mircoservices_28.limits_service.controller;

import com.abc.psk.simple_mircoservices_28.limits_service.configuration.Configuration;
import com.abc.psk.simple_mircoservices_28.limits_service.entity.LimitConfiguration;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LimitConfigurationController {

    @Autowired private Configuration configuration;

    @GetMapping("/limits")
    public LimitConfiguration retrieveLimitsFromConfiguration() {
        return new LimitConfiguration(configuration.getMaximum(),configuration.getMinimum());
    }

    @GetMapping("/fault-tolerance-example")
    @HystrixCommand(fallbackMethod = "fallbackRetrieveConfiguration")
    public LimitConfiguration retrieveConfiguration() {
        throw new RuntimeException("Not Available");
    }

    public LimitConfiguration fallbackRetrieveConfiguration() {
        return new LimitConfiguration(999, 9);
    }
}
