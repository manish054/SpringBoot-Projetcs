package com.jwtnovember.jwthandson.model;

import java.util.Collection;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


public class UserPrincipal implements UserDetails{
    @Autowired
    User user;

    public UserPrincipal(User user){
        this.user = user;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        System.out.println("user role---"+user.getRole());
        return Collections.singleton(new SimpleGrantedAuthority("ROLE_"+user.getRole()));    
    }

    @Override
    public String getPassword() {
       return user.getPassword(); 
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

}
