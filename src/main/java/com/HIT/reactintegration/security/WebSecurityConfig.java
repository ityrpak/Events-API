package com.HIT.reactintegration.security;

import com.HIT.reactintegration.configs.DelegatePasswordEncode;
import com.HIT.reactintegration.services.UserServiceImpl;
import com.HIT.reactintegration.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import javax.sql.DataSource;

@EnableGlobalMethodSecurity(
        prePostEnabled = true
)
@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DelegatePasswordEncode passwordEncode;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests().antMatchers("/").permitAll()
                .and()
                .addFilter(new AuthenticationJwtTokenFilter(jwtTokenUtil, authenticationManagerBean()));
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userService)
                .and()
                .jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(passwordEncode.getDelegatingEncoder("bcrypt"))
                .usersByUsernameQuery("SELECT nickname, password, enabled "
                        + "FROM users "
                        + "WHERE nickname = ?")
                .authoritiesByUsernameQuery("SELECT nickname, role_id "
                        + "FROM users "
                        + "WHERE nickname = ?");
    }


}
