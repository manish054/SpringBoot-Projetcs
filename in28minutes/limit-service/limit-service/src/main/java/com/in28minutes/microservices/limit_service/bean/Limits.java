package com.in28minutes.microservices.limit_service.bean;

import org.springframework.cloud.context.config.annotation.RefreshScope;

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
public class Limits {
    private int maximum;
    private int minimum;
}
