package com.scm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scm.entities.Contact;
import com.scm.serviceimplementation.ConatctServiceImplementation;

@RestController
@RequestMapping("user/api")
public class ApiController {

    @Autowired
    private ConatctServiceImplementation contactService;

    @GetMapping("/contacts/{id}")
    public Contact getContact(@PathVariable int id){
        System.out.println("***contactService.getById(id).getBody()***"+contactService.getById(id).getBody());
        return (Contact) contactService.getById(id).getBody();
    }
}
