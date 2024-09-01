package com.scm.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.scm.helper.Helper;
import com.scm.helper.Message;
import com.scm.helper.MessageType;
import com.scm.serviceimplementation.UserServiceImplementation;

import jakarta.servlet.http.HttpSession;

@Controller
public class EmailController {

    @Autowired
    private UserServiceImplementation userServiceImplementation;

    @GetMapping("auth/verify-email")
    public String verifyEmail(@RequestParam String token, HttpSession session){
        System.out.println("--token--"+token);
        if(userServiceImplementation.findByEmailToken(token).getBody() != null){
            System.out.println("***userServiceImplementation.findByEmailToken(token)***"+userServiceImplementation.findByEmailToken(token));
            return "user/emailVerified";
        }
        return "user/emailVerifiactionFailedError";
    }
}
