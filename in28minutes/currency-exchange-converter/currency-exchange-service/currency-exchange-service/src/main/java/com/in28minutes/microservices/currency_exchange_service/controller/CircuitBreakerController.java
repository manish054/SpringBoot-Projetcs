package com.in28minutes.microservices.currency_exchange_service.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
public class CircuitBreakerController {

    private Logger logger = LoggerFactory.getLogger(CircuitBreakerController.class);
    @GetMapping("/sample-api")
    // @Retry(name = "sample-api", fallbackMethod = "hardCodedResponse")
    @CircuitBreaker(name = "sample-api", fallbackMethod = "hardCodedResponse")
    @RateLimiter(name = "default")
    //rate limiter will define in how much time an api should be called
    /*
     * resilience4j.ratelimiter.instances.default.limit-for-period=2
       resilience4j.ratelimiter.instances.default.limit-refresh-period=10s
     */
    public String sampleApi(){
        logger.info("Sample api called ");
        ResponseEntity<String> responseEntity = 
            new RestTemplate().getForEntity("http://localhost:8080/sample-dummy-api", 
        String.class);
        return responseEntity.getBody();
    }

    public String hardCodedResponse(Exception ex){
        return "Message from Hard Coded Response";
    }
}
