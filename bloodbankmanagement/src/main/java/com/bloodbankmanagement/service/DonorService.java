package com.bloodbankmanagement.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.bloodbankmanagement.model.Donor;
import com.bloodbankmanagement.model.DonorsTemplate;
import com.bloodbankmanagement.model.User;
import com.bloodbankmanagement.repository.DonorRepository;
import com.bloodbankmanagement.repository.UserRepository;

@Service
public class DonorService {
    @Autowired
    DonorRepository donorRepository;

    @Autowired
    UserRepository userRepository;

    public Donor donorRegistration(Donor donor){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("logged User---"+authentication.getName());
        String username = authentication.getName();
        User loggedUser = userRepository.findByUsername(username);
        System.out.println(loggedUser.toString());
        try{
            Donor donr = new Donor();
            donr.setDonorid(UUID.randomUUID().toString());
            donr.setDbloodgroup(donor.getDbloodgroup());
            donr.setDcity(donor.getDcity());
            donr.setUserUserId(loggedUser.getUserid());
            donr.setDfirstname(loggedUser.getFirstname());
            donr.setDlastname(loggedUser.getLastname());
            donr.setDcontactnum(loggedUser.getContactnum());
            return donorRepository.save(donr);
        }catch(Exception e){
            System.out.println("Exception ---"+e.getMessage());
            return null;
        }
        
    }

    public List<DonorsTemplate> getDonors(String bloodgroup, String city){
        List<Donor> donorsList =  donorRepository.findByDbloodgroupAndDcityIgnoreCase(bloodgroup, city);
        List<DonorsTemplate> dTemplatesList = new ArrayList<>();
        donorsList.forEach(donor ->{
            DonorsTemplate donorsTemplate = new DonorsTemplate();
            donorsTemplate.setDdid(donor.getDonorid());
            donorsTemplate.setDdfirstname(donor.getDfirstname());
            donorsTemplate.setDdlastname(donor.getDlastname());
            donorsTemplate.setDdcity(donor.getDcity());
            donorsTemplate.setDdcontactnum(donor.getDcontactnum());
            donorsTemplate.setDdbloodgroup(donor.getDbloodgroup());
            dTemplatesList.add(donorsTemplate);
        });
        return dTemplatesList;
    }
}
