package com.project.MyProject.Task.User.Service.config;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.crypto.SecretKey;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

@Component
public class Jwtprovider {
    static SecretKey key = Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes());

    public static String generateToken(Authentication auth) {
        Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
        String roles = populateAuthorities(authorities);

        String jws = Jwts.builder()
                .setSubject(auth.getName()) 
                .claim("email", auth.getName())
                .claim("authorities", roles)
                .setIssuedAt(new Date()) 
                .setExpiration(new Date(System.currentTimeMillis() + 86400000))
                .signWith(key) // ✅ Signs Token
                .compact();

        System.out.println("Generated Jwt: " + jws);
        return jws;
    }

    public static String populateAuthorities(Collection<? extends GrantedAuthority> collection) {
        Set<String> auths = new HashSet<>();
        for (GrantedAuthority authority : collection) {
            auths.add(authority.getAuthority());
        }
        return String.join(",", auths);
    }

    public static String getEmailFromJwtToken(String jwt) {
        try {
            if (jwt.startsWith("Bearer ")) {
                jwt = jwt.substring(7);
            }

            Claims claims = Jwts.parser()
                    .verifyWith(key) 
                    .build()
                    .parseSignedClaims(jwt) 
                    .getPayload(); 

            return claims.get("email", String.class);

        } catch (Exception e) {
            System.out.println("JWT Error: " + e.getMessage());
            return null;
        }
    }
}
