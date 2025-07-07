package com.bloodbankmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bloodbankmanagement.model.Donor;
import com.bloodbankmanagement.service.DonorService;

@RestController
@RequestMapping("/donor")
public class DonorController {
    
    @Autowired
    DonorService donorService;

    @PostMapping("/register")
    public Donor register(@RequestBody Donor donor){
        System.out.println(donor.getDcity()+"---"+donor.getDbloodgroup());
        return donorService.donorRegistration(donor);
    }
}
