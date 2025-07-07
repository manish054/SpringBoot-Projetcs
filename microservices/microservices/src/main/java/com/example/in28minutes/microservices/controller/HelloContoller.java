package com.example.in28minutes.microservices.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HelloContoller {
    @Autowired
    MessageSource messageSource;

    @GetMapping("/good-morning")
    public String goodMorningInternationalization(){
        Locale locale = LocaleContextHolder.getLocale();
        System.out.println(locale);
        return messageSource.getMessage("good.morning.message", null, "Default", locale);
    }
}
