package com.HIT.reactintegration.entities;

import com.HIT.reactintegration.enums.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity(name = "ROLES")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Role implements GrantedAuthority {

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    private Long id;

    @Column(name = "role_name", updatable = false, nullable = false)
    @Enumerated(EnumType.STRING)
    private RoleEnum roleName;

    @Override
    public String getAuthority() {
        return this.getRoleName().toString();
    }
}
