package com.HIT.reactintegration.security;

import com.HIT.reactintegration.services.UserServiceImpl;
import com.HIT.reactintegration.utils.JwtTokenUtil;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@Slf4j
public class AuthenticationJwtTokenFilter extends UsernamePasswordAuthenticationFilter {

    private final String AUTHORIZATION = "Authorization";
    private final String REJECTION = "Request Rejected: ";

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserServiceImpl userService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        if (httpRequest.getHeader(AUTHORIZATION) != null && httpRequest.getHeader(AUTHORIZATION).length()>7){

            final String token = httpRequest.getHeader(AUTHORIZATION).substring(7);

            try {
                final UserDetails userDetails = userService.loadUserByUsername(jwtTokenUtil.getNicknameFromToken(token));
                Authentication authentication = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );

                if (userDetails.isEnabled() && new Date().before(jwtTokenUtil.getExpirationDate(token))) {
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }

            } catch (SignatureException exception) {
                log.warn(REJECTION + exception);
            } catch (ExpiredJwtException exception){
                log.warn(REJECTION + exception);
            } catch (MalformedJwtException exception){
                log.warn(REJECTION + exception);
            }

        }

        chain.doFilter(httpRequest,httpResponse);
    }


}
