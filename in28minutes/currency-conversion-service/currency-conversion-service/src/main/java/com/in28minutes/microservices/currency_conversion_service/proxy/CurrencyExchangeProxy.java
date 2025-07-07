package com.in28minutes.microservices.currency_conversion_service.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.in28minutes.microservices.currency_conversion_service.entity.CurrencyConversion;

//Here name will be the application name of service we need 
// and url will be the local host port of that application
// @FeignClient(name="currency-exchange", url = "localhost:8000")

 
/*
 * removing url from feignClient will allow it to directly communicate between feign and Eureka,
 * creating required number of currency-exchange instances as well as load balancing
 */
@FeignClient(name = "currency-exchange")
public interface CurrencyExchangeProxy {
    
    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyConversion retriveExchangeValue(@PathVariable("from") String from, 
        @PathVariable("to") String to);
}
