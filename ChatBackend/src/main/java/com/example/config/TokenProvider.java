package com.example.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Duration;
import java.time.Instant;
import java.util.*;

@Service
public class TokenProvider {
    SecretKey key = Keys.hmacShaKeyFor(Base64.getDecoder().decode(JWT_CONSTANT.SECRET_KEY));

    public String generateToken(Authentication authentication) {
        
        return Jwts.builder()
                .header().empty().add("typ" , "jwt")
                .and()
                .expiration(Date.from(Instant.now().plus(Duration.ofHours(100))))
                .claim("email" , authentication.getName())
                .signWith(key)
                .compact();
    }

    private String populateAuthorities(Collection<? extends GrantedAuthority> authorities) {
        Set<String> auth = new HashSet<>();

        for (GrantedAuthority authority : authorities) {
            auth.add(authority.getAuthority());
        }

        return String.join(",", auth);
    }

    public String getEmailFromJwtToken (String jwt) {
        if (jwt.startsWith("Bearer ")) {
            jwt=jwt.substring(7);
        }
        Claims claims = Jwts.parser().setSigningKey(key).build()
                .parseClaimsJws(jwt).getBody();

        return String.valueOf(claims.get("email"));
    }
}
