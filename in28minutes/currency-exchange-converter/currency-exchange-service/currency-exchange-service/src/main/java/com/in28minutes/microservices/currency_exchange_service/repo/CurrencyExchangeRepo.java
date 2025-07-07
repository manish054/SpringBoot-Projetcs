package com.in28minutes.microservices.currency_exchange_service.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.in28minutes.microservices.currency_exchange_service.entity.CurrencyExchange;

@Repository
public interface CurrencyExchangeRepo extends JpaRepository<CurrencyExchange, Long>{
    CurrencyExchange findByCurrencyFromAndCurrencyTo(String from, String to);
}
