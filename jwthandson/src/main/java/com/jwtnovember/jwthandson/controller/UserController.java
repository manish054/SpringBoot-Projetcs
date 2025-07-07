package com.jwtnovember.jwthandson.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @GetMapping("/dashboard")
    public String dashboard(){
        return "Here is the USER Dash-Board";
    }
}
