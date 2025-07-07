package com.example.in28minutes.microservices.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.in28minutes.microservices.entity.Users;
import com.example.in28minutes.microservices.repo.UserRepo;

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;

    public List<Users> getUsers(){
        return userRepo.findAll();
    }

    public Users saveUser(){
        Users user = new Users();
        user.setName("manish");
        user.setBirthDate(LocalDateTime.now());
        return userRepo.save(user);
    }

    public Users getUser(Integer id){
        return userRepo.findById(id).get();
    }

    public String deleteById(Integer id){
        Users user = userRepo.findById(id).get();
        if(user != null){
            userRepo.deleteById(id);
            return "User Deleted from Database";
        }
        return "No User Found";
    }
}
