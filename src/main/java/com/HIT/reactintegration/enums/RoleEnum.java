package com.HIT.reactintegration.enums;

import org.springframework.security.core.GrantedAuthority;

public enum RoleEnum implements GrantedAuthority {

    ADMIN_ROLE,
    MODERATOR_ROLE,
    USER_ROLE;

    @Override
    public String getAuthority() {
        return this.name();
    }
}
