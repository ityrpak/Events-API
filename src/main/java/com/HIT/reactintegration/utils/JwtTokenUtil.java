package com.HIT.reactintegration.utils;

import com.HIT.reactintegration.dtos.responsesdto.SuccessResponseDTO;
import com.HIT.reactintegration.dtos.userdto.UserLoginDTO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtTokenUtil implements Serializable {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration.time}")
    private Long expirationTime;

    public SuccessResponseDTO generateToken(UserLoginDTO userLoginDTO, UserDetails details){
        final HashMap<String, Object> claims = new HashMap<>();
        claims.put("role", details.getAuthorities());
        return SuccessResponseDTO.builder()
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK.getReasonPhrase())
                .data(Map.of("Token", "Bearer " + Jwts
                        .builder()
                        .setClaims(claims)
                        .setSubject(userLoginDTO.getNickname())
                        .setIssuedAt(new Date(System.currentTimeMillis()))
                        .setExpiration(new Date(System.currentTimeMillis()+expirationTime))
                        .signWith(SignatureAlgorithm.HS512,secret)
                        .compact()))
                .build();
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
