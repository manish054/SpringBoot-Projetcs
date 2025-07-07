package com.jwtnovember.jwthandson.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.jwtnovember.jwthandson.model.User;
import com.jwtnovember.jwthandson.model.UserPrincipal;
import com.jwtnovember.jwthandson.repo.UserRepository;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository userRepo;

    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);

    public String register(User user){
        try {
            User newUser = new User(UUID.randomUUID().toString(), user.getUsername(), user.getPassword(), user.getRole());
            newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));
            userRepo.save(newUser);
            User repoUser = userRepo.findByUsername(newUser.getUsername());
            System.out.println("repoUser username---"+repoUser.getUsername());
            return newUser.toString();
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Exception in Userservice---"+e);
            return "Error!!! check fields";
        }
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("loadUserByUsername---"+username);
        User user = userRepo.findByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException(username);
        }
        System.out.println("loadUserByUsername username---"+user.getUsername());
        return new UserPrincipal(user);
    }
}
