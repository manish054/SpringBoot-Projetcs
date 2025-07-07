package com.jwtnovember.jwthandson.configuration;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import com.jwtnovember.jwthandson.service.JwtService;
import com.jwtnovember.jwthandson.service.UserService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Configuration
public class JwtFilter extends OncePerRequestFilter{

    @Autowired
    JwtService jwtService;

    @Autowired
    ApplicationContext applicationContext;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String authToken = request.getHeader("Authorization");
        String token = null;
        String username = null;
        if(authToken != null && authToken.startsWith("Bearer ")){
            token = authToken.substring(7);
            username = jwtService.extractUsername(token);
            System.out.println("token---"+token);
            System.out.println("username token---"+token);
        }
        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails user = applicationContext.getBean(UserService.class).loadUserByUsername(username);
            System.out.println("jwtFilter user---"+user.getUsername());
            if(jwtService.isValidToken(token, user)){
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user, null,  user.getAuthorities());
                auth.setDetails(new WebAuthenticationDetails(request));
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }
        filterChain.doFilter(request, response);
    }

}
