package com.jwtnovember.jwthandson.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.jwtnovember.jwthandson.model.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    String secret = "hajgdiebyweiyryiwnyqwetqnygwqyzetfqtefqimRWTFRv7342hasgdh";
    int validity = 1000*60*30;
    public String generateToken(String username){
        Map<String, Object> claim = new HashMap<>();
        return Jwts.builder().header().add("type", "jwt")
                            .and()
                            .claims()
                            .add(claim)
                            .and()
                            .subject(username)
                            .issuedAt(new Date(System.currentTimeMillis()))
                            .expiration(new Date(System.currentTimeMillis() + validity))
                            .signWith(getKey())
                            .compact();

    }

    public SecretKey getKey(){
        byte[] key = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(key);
    }

    public String extractUsername(String token){
        return extractClaims(token, Claims::getSubject);
    }

    public <T> T extractClaims(String token, Function<Claims, T> claimsResolver){
        final Claims claim = extractAllClaims(token);
        return claimsResolver.apply(claim);
    }

    public Claims extractAllClaims(String token){
        return Jwts.parser()
                    .verifyWith(getKey())
                    .build()
                    .parseClaimsJws(token)
                    .getPayload();
    }

    public boolean isValidToken(String token, UserDetails user){
        String username = extractUsername(token);
        if(username.equals(user.getUsername()) && isTokenExpired(token)){
            return true;
        }
        return false;
    }

    public boolean isTokenExpired(String token){
        return extractExpiration(token);
    }

    public boolean extractExpiration(String token){
       return extractClaims(token, Claims::getExpiration).after(new Date());
    }
}
