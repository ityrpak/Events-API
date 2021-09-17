package com.HIT.reactintegration.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class BCryptConfig extends BCryptPasswordEncoder {

    public BCryptPasswordEncoder encode() {
        return new BCryptPasswordEncoder(5);
    }
}
