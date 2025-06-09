package com.hexJune.CodingTest.utility;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtility {

    String secretKey = "LMS_HEX_MAY_720448618103782308902";
    Key key;
    {
        key = Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    /*Method ro Create JWT Token*/
    public String createToken(String email){

        long expirationTimeInMills = 43200000;// 12 hrs
        System.out.println("I am in create token method");
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expirationTimeInMills))
                .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()), SignatureAlgorithm.HS256)
                .compact();
    }

    /* Method to verify the Token */
    public boolean verifyToken(String token, String email){
        String extractedEmail = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
        Date expirationDate = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();

        return extractedEmail.equals(email) && new Date().before(expirationDate);

    }

    public String extractUsername(String token) {
        return  Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
