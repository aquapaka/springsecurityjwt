package com.aquapaka.springsecurityjwt.repository;

import com.aquapaka.springsecurityjwt.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    AppUser findByUsername(String username);
}
