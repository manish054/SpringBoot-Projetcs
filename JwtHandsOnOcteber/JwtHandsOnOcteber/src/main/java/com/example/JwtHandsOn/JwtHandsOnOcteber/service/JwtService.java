package com.example.JwtHandsOn.JwtHandsOnOcteber.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    String secretKey = "bjhawsyiagxbJNSUIEH12512456haksehgywbgeiycwywgrugeynxw";
    long validity = 1000*60*30;
    // public JwtService(){
    //     KeyGenerator keyGenerator;
    //     try {
    //         keyGenerator = KeyGenerator.getInstance("HmacSHA256");
    //         SecretKey sKey = keyGenerator.generateKey();
    //         secretKey = Base64.getEncoder().encodeToString(sKey.getEncoded());
    //         System.out.println("secretKey---"+secretKey);
    //     } catch (NoSuchAlgorithmException e) {
    //         // TODO Auto-generated catch block
    //         e.printStackTrace();
    //     }
        
    // }

    public String generateToken(String username){
        Map<String, Object> claim = new HashMap<>();
        // claim.put("type", "jwt");
        return Jwts.builder().header().add("type", "jwt")
                    .and()
                    .claims()
                    .add(claim)
                    .subject(username)
                    .issuedAt(new Date(System.currentTimeMillis()))
                    .expiration(new Date(System.currentTimeMillis()+validity))
                    .and()
                    .signWith(getKey())
                    .compact();
    }

    private SecretKey getKey() {
        byte[] key = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(key);
    }

    public String extractUsername(String token){
        return extractClaims(token, Claims::getSubject);
    }

    public <T> T extractClaims(String token, Function<Claims, T> claimResolver){
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    public Claims extractAllClaims(String token){
        return Jwts.parser()
                    .verifyWith(getKey())
                    .build()
                    .parseClaimsJws(token)
                    .getPayload();
    }

    public boolean isValidateToken(String token, UserDetails user) {
        String username = extractUsername(token);
        if(username.equals(user.getUsername()) && isTokenExpired(token)){
            return true;
        }
        return false;
    }

    public boolean isTokenExpired(String token){
        return extractExpiration(token).after(new Date());
    }

    public Date extractExpiration(String token){
        return extractClaims(token, Claims::getExpiration);
    }

}
