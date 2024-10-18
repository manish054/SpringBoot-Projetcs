package com.example.JwtHandsOn.JwtHandsOnOcteber.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.JwtHandsOn.JwtHandsOnOcteber.model.UserPrincipal;
import com.example.JwtHandsOn.JwtHandsOnOcteber.model.Users;
import com.example.JwtHandsOn.JwtHandsOnOcteber.repo.UserRepo;

@Service
public class UserService implements UserDetailsService{

    @Autowired
    UserRepo userRepo;

    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);

    public Users register() {
       Users user = new Users(UUID.randomUUID().toString(), "bittu", "bittu");
       user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
       userRepo.save(user);
       Users user1 = userRepo.findByUsername(user.getUsername());
       System.out.println("username: "+user1.getUsername());
       return userRepo.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userRepo.findByUsername(username);
        System.out.println("user in loadUserByUsername: "+user.getUsername());
        if(user != null)
            return new UserPrincipal(user);
        throw new UnsupportedOperationException("User not found in loadUserByUsername");
    }

    public String login(Users user) {
        return "";
    }

}
