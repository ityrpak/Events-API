package com.HIT.reactintegration.repositories;

import com.HIT.reactintegration.entities.Role;
import com.HIT.reactintegration.enums.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID> {
    Role findByRoleName(RoleEnum roleName);
}
