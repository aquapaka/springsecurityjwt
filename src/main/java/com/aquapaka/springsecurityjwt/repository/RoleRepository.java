package com.aquapaka.springsecurityjwt.repository;

import com.aquapaka.springsecurityjwt.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
