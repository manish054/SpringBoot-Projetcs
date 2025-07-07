package com.bloodbankmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bloodbankmanagement.model.DonorsTemplate;
import com.bloodbankmanagement.service.DonorService;

@RestController
@RequestMapping("/requestor")
public class RequestorController {

    @Autowired
    DonorService donorService;

    @GetMapping("/searchdonor/{bloodgroup}/{city}")
    public List<DonorsTemplate> searchDonor(@PathVariable String bloodgroup, 
    @PathVariable String city){
        List<DonorsTemplate> donorsList = donorService.getDonors(bloodgroup, city);
        return donorsList;
    }
}
