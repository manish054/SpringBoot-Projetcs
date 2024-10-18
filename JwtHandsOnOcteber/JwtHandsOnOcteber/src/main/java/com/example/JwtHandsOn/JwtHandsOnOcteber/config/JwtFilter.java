package com.example.JwtHandsOn.JwtHandsOnOcteber.config;

import java.io.IOException;
import java.nio.file.attribute.UserPrincipal;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.JwtHandsOn.JwtHandsOnOcteber.repo.UserRepo;
import com.example.JwtHandsOn.JwtHandsOnOcteber.service.JwtService;
import com.example.JwtHandsOn.JwtHandsOnOcteber.service.UserService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter{

    @Autowired
    JwtService jwtService;

    @Autowired
    ApplicationContext context;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
       String authHeader = request.getHeader("Authorization");
       String username = null;
       String token = null;
       if(authHeader != null && authHeader.startsWith("Bearer ")){
        token = authHeader.substring(7);
        username = jwtService.extractUsername(token);
       }
       if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
        UserDetails user = context.getBean(UserService.class).loadUserByUsername(username);

        if(jwtService.isValidateToken(token, user)){
            UsernamePasswordAuthenticationToken authToken = 
            new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authToken);
        }
       }
       filterChain.doFilter(request, response);
    }

}
