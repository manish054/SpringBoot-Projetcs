package com.in28minutes.microservices.currency_exchange_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.in28minutes.microservices.currency_exchange_service.entity.CurrencyExchange;
import com.in28minutes.microservices.currency_exchange_service.repo.CurrencyExchangeRepo;

@Service
public class CurrExchngService {
    
    @Autowired
    CurrencyExchangeRepo currencyExchangeRepo;

    public CurrencyExchange retriveExchangeValue(String from, String to){
        return currencyExchangeRepo.findByCurrencyFromAndCurrencyTo(from, to);
    }
}
