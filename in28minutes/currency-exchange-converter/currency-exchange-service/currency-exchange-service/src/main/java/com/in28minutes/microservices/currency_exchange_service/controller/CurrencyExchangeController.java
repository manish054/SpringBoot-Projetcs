package com.in28minutes.microservices.currency_exchange_service.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.in28minutes.microservices.currency_exchange_service.entity.CurrencyExchange;
import com.in28minutes.microservices.currency_exchange_service.service.CurrExchngService;

@RestController
public class CurrencyExchangeController {
    
    //will be getting the application.proprties values here in Environment
   @Autowired
   private Environment environment;

   @Autowired
   private CurrExchngService currExchngService;
   
    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyExchange retriveExchangeValue(@PathVariable("from") String from, 
        @PathVariable("to") String to){
            CurrencyExchange currencyExchange = 
                    currExchngService.retriveExchangeValue(from, to);
            if(currencyExchange == null){
                throw new RuntimeException("Conversion pair "+from+ " to "+to+" not found");
            }
            currencyExchange.setEnvironment(environment.getProperty("local.server.port"));
            return currencyExchange;
        }

    @GetMapping("/")
    public String home(){
        return "Currency-Exchange Sevice";
    }
}
