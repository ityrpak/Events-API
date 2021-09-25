package com.HIT.reactintegration.security;

import com.HIT.reactintegration.dtos.exceptiondto.ExceptionDTO;
import com.HIT.reactintegration.dtos.responsesdto.ErrorResponseDTO;
import com.HIT.reactintegration.dtos.responsesdto.SuccessResponseDTO;
import com.HIT.reactintegration.dtos.userdto.UserLoginDTO;
import com.HIT.reactintegration.entities.User;
import com.HIT.reactintegration.utils.JwtTokenUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@Slf4j
public class AuthenticationJwtTokenFilter extends UsernamePasswordAuthenticationFilter {

    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;

    public AuthenticationJwtTokenFilter(JwtTokenUtil jwtTokenUtil, AuthenticationManager authenticationManager){
        this.jwtTokenUtil = jwtTokenUtil;
        this.authenticationManager = authenticationManager;
        setFilterProcessesUrl("/login");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        try {
            UserLoginDTO userLoginDTO = new ObjectMapper()
                    .readValue(request.getInputStream(), UserLoginDTO.class);

            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            userLoginDTO.getNickname(),
                            userLoginDTO.getPassword()
                    )
            );
        } catch (IOException ex){
            throw new RuntimeException(ex);
        }

    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {

        User authenticatedUser = (User) authResult.getPrincipal();

        response.setStatus(200);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        String successResponseDTO = new Gson().toJson(SuccessResponseDTO.builder()
                        .code(HttpStatus.OK.value())
                        .status(HttpStatus.OK.getReasonPhrase())
                        .data(Map.of("Token", jwtTokenUtil.generateToken(authenticatedUser)))
                .build());
        out.print(successResponseDTO);
        out.flush();

    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {

        response.setStatus(401);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        String errorResponseDTO = new Gson().toJson(ErrorResponseDTO.builder()
                        .code(HttpStatus.UNAUTHORIZED.value())
                        .status(HttpStatus.UNAUTHORIZED.getReasonPhrase())
                        .exceptions(ExceptionDTO.builder()
                                .exceptionName("AuthenticationFailed")
                                .exceptionMessage("Bad credentials")
                                .build())
                .build());
        PrintWriter out = response.getWriter();
        out.print(errorResponseDTO);
        out.flush();

    }


}
