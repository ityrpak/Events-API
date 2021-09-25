package com.HIT.reactintegration.security;

import com.HIT.reactintegration.dtos.exceptiondto.ExceptionDTO;
import com.HIT.reactintegration.dtos.responsesdto.ErrorResponseDTO;
import com.HIT.reactintegration.enums.RoleEnum;
import com.HIT.reactintegration.utils.JwtTokenUtil;
import com.google.gson.Gson;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashSet;

@Slf4j
public class AuthorizationJwtTokenFilter extends BasicAuthenticationFilter {

    private static String AUTHORIZATION = "Authorization";
    private static String TOKEN_PREFIX = "Bearer ";

    private JwtTokenUtil jwtTokenUtil;

    public AuthorizationJwtTokenFilter(JwtTokenUtil jwtTokenUtil, AuthenticationManager authenticationManager) {
        super(authenticationManager);
        this.jwtTokenUtil = jwtTokenUtil;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        String header = request.getHeader(AUTHORIZATION);

        try {

            if (header == null) {
                chain.doFilter(request, response);
                    if (response.getStatus() == 200) {
                        return;
                    } else if (response.getStatus() == 401 || response.getStatus() == 403) {
                        throw new AccessDeniedException("");
                    }
            }

            if (header != null && !header.startsWith(TOKEN_PREFIX)) {
                throw new UnsupportedJwtException("Bearer prefix malformed or null");
            } else if (header != null && header.startsWith(TOKEN_PREFIX)) {
            UsernamePasswordAuthenticationToken authenticationToken = getAuthentication(request, header);
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            chain.doFilter(request,response);
            }

        } catch (MalformedJwtException ex) {
            setBadRequestResponse(response, ex);
        } catch (UnsupportedJwtException ex) {
            setBadRequestResponse(response, ex);
        } catch (ExpiredJwtException ex) {
            setBadRequestResponse(response, ex);
        } catch (SignatureException ex) {
            setBadRequestResponse(response, ex);
        } catch (AccessDeniedException ex) {
            SecurityContextHolder.getContext().setAuthentication(null);
        }

    }

    private void setBadRequestResponse(HttpServletResponse response, Exception ex) throws IOException {
        response.setStatus(400);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        String errorResponseDTO = new Gson().toJson(ErrorResponseDTO.builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .status(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .exceptions(ExceptionDTO.builder()
                        .exceptionName(ex.getClass().getSimpleName())
                        .exceptionMessage(ex.getMessage())
                        .build())
                .build());
        PrintWriter out = response.getWriter();
        out.print(errorResponseDTO);
        out.flush();
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request, String header) {

        if (header != null) {
            String token = header.substring(TOKEN_PREFIX.length());

            String user = jwtTokenUtil.getNicknameFromToken(token);

            HashSet<RoleEnum> authorities = new HashSet<>();
            authorities.add(jwtTokenUtil.getRoleFromToken(token));

            if (user != null && jwtTokenUtil.getExpirationDate(token).after(new Date())) {
                return new UsernamePasswordAuthenticationToken(user, null, authorities);
            }

        }
        return null;
    }
}
