package com.HIT.reactintegration.enums;

import org.springframework.security.core.GrantedAuthority;

public enum RoleEnum implements GrantedAuthority {

    ROLE_ADMIN,
    ROLE_MODERATOR,
    ROLE_USER;

    @Override
    public String getAuthority() {
        return this.name();
    }
}
