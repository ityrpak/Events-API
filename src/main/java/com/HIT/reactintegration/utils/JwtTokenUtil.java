package com.HIT.reactintegration.utils;

import com.HIT.reactintegration.entities.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.function.Function;

@Component
public class JwtTokenUtil implements Serializable {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration.time}")
    private Long expirationTime;

    public String generateToken(User authenticatedUser){

        final HashMap<String, Object> claims = new HashMap<>();
            claims.put("role", authenticatedUser.getAuthorities());

            return "Bearer " + Jwts
                            .builder()
                            .setClaims(claims)
                            .setSubject(authenticatedUser.getNickname())
                            .setIssuedAt(new Date(System.currentTimeMillis()))
                            .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                            .signWith(SignatureAlgorithm.HS512, secret)
                            .compact();

    }

    public String getNicknameFromToken(String jwtToken) {
        return getClaimFromToken(jwtToken, Claims::getSubject);
    }

    public String getRoleFromToken(String jwtToken) {
        return getClaimFromToken(jwtToken,
                (Claims claims) -> claims.get("role").toString());
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver){
        Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) throws SignatureException {
        Claims allClaimsFromToken = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        return allClaimsFromToken;
    }

    public Date getExpirationDate(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }


}
