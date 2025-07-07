package com.jwtnovember.jwthandson.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jwtnovember.jwthandson.service.JwtService;
import com.jwtnovember.jwthandson.service.UserService;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    JwtService jwtService;
    
    @Autowired
    UserService userService;

    @GetMapping("/dashboard")
    public ResponseEntity<Object> dashboard(@RequestHeader("jwt") String jwt){
        System.out.println("RequestHeader---"+jwt);
        String username = jwtService.extractUsername(jwt);
        UserDetails user = userService.loadUserByUsername(username);
        
        try {
            if(jwtService.isValidToken(jwt, user)){
                return ResponseEntity.ok("Welcome to ADMIN DashBoard");
            }
        } catch (Exception e) {
            // TODO: handle exception
            return ResponseEntity.badRequest().body("Unauthorized user");
        }
        
        return ResponseEntity.badRequest().body("Unauthorized user");
       
    }
}
