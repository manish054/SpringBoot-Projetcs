package com.bloodbankmanagement.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.bloodbankmanagement.model.User;
import com.bloodbankmanagement.model.UserPrincipal;
import com.bloodbankmanagement.repository.UserRepository;

@Service
public class UserService implements UserDetailsService{

    @Autowired
    UserRepository userRepository;

    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);

    public User register(User user){
        try {
            User newUser = new User();
		newUser.setUserid(UUID.randomUUID().toString());
		newUser.setFirstname(user.getFirstname());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		newUser.setLastname(user.getLastname());
        newUser.setUsername(user.getUsername());
        newUser.setContactnum(user.getContactnum());
        return userRepository.save(newUser);
        } catch (Exception e) {
            return null;
        }
    }

    public User getUser(String userid){
        return userRepository.findByUserid(userid);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO Auto-generated method stub
        User user = userRepository.findByUsername(username);
        if(user != null){
            return new UserPrincipal(user);
        }
        throw new UnsupportedOperationException("Unimplemented method 'loadUserByUsername'");
    }
}
