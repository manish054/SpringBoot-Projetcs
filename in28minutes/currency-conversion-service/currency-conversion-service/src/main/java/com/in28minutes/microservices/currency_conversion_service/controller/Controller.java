package com.in28minutes.microservices.currency_conversion_service.controller;

import java.math.BigDecimal;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.in28minutes.microservices.currency_conversion_service.entity.CurrencyConversion;
import com.in28minutes.microservices.currency_conversion_service.proxy.CurrencyExchangeProxy;

@RestController
public class Controller {

    @Autowired
    private CurrencyExchangeProxy currencyExchangeProxy;

    @GetMapping("/currency-conversion/from/{from}/to/{to}/qunatity/{quantity}")
    public CurrencyConversion getExchangeValue(@PathVariable("from") String from,
            @PathVariable("to") String to, @PathVariable("quantity") BigDecimal quantity){
                System.out.println("from---"+from);
                System.out.println("to---"+to);
                System.out.println("quantity---"+quantity);

                HashMap<String, String> uriVariables = new HashMap<>();
                uriVariables.put("from", from);
                uriVariables.put("to", to);

                /*these are the steps if we are not using Feign REST, directly mapping the url to be communicated
                    then RestTemplate will convert the response in CurrencyConversion.class

                    it will map all the variables from CurrencyExchange.class to CurrencyConversion.class that has same
                    variable name and type

                    if matching variable not found then it will assign null there.
                */
                ResponseEntity<CurrencyConversion> responseEntity =
                     new RestTemplate().getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}", 
                CurrencyConversion.class,
                uriVariables);

                CurrencyConversion currencyConversion = responseEntity.getBody();
                System.out.println("currencyConversion.toString---"+currencyConversion.toString());
                currencyConversion.setEnvironment(currencyConversion.getEnvironment() +" "+ "Rest Template");
                currencyConversion.setQunatity(quantity);
                currencyConversion.setTotalCalculatedAmount(quantity.multiply(currencyConversion.getConversionMultiple()));

                return currencyConversion;
    }

    /*
     * using open feign, we can reduce the lines of code and we can make url dynamic
     * In above get url, we are hard coding the url in RestTemplate
     * 
     * How it works?
     * when we will hit the url "/currency-conversion-feign/from/{from}/to/{to}/qunatity/{quantity}"
     * it will go to the localhost:8000 on which currency-exchange is running
     * then it will request hit the url "http://localhost:8000/currency-exchange/from/USD/to/INR"
     * and then it will take the response and cast it to CurrencyConversion.java
     * 
     * Why used CurrencyExchangeProxy?
     * In above getExchangeValue method, we are using RestTemplate to cast the response to CurrencyConversion.java
     * and it increases the lines of code
     * So, we have created an interface CurrencyExchangeProxy and mentioned the respctive method whose 
     * response will be cast to CurrencyConversion.java
     * 
     */
    @GetMapping("/currency-conversion-feign/from/{from}/to/{to}/qunatity/{quantity}")
    public CurrencyConversion getExchangeValuefeign(@PathVariable("from") String from,
            @PathVariable("to") String to, @PathVariable("quantity") BigDecimal quantity){
                
                // currencyExchangeProxy.retriveExchangeValue(from, to) will return CurrencyConversion.java
                CurrencyConversion currencyConversion = currencyExchangeProxy.retriveExchangeValue(from, to);
                System.out.println("currencyConversion.toString---"+currencyConversion.toString());
                currencyConversion.setEnvironment(currencyConversion.getEnvironment() +" "+ "Feign");
                currencyConversion.setQunatity(quantity);
                currencyConversion.setTotalCalculatedAmount(quantity.multiply(currencyConversion.getConversionMultiple()));

                return currencyConversion;
    }

}
