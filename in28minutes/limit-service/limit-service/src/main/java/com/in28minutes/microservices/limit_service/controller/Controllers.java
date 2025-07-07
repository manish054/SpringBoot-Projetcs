package com.in28minutes.microservices.limit_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.in28minutes.microservices.limit_service.bean.Limits;
import com.in28minutes.microservices.limit_service.config.Configuration;

@RestController
@RequestMapping("/")
public class Controllers {
    
    @Autowired
    private Configuration configuration;

    @GetMapping("limits")
    public Limits retrieveLimits(){
        return new Limits(configuration.getMaximum(), configuration.getMinimum());
    }
}
