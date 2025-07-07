package com.in28minutes.microservices.currency_conversion_service.entity;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CurrencyConversion {
    private Long id;
    private String currencyFrom;
    private String currencyTo;
    private BigDecimal conversionMultiple;
    private BigDecimal qunatity;
    private BigDecimal totalCalculatedAmount;
    private String environment;
}
