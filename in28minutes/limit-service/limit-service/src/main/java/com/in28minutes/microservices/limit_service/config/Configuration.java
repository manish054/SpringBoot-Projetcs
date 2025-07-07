package com.in28minutes.microservices.limit_service.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Component
@ConfigurationProperties("limits-service")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@RefreshScope
public class Configuration {
    private int maximum;
    private int minimum;
}
