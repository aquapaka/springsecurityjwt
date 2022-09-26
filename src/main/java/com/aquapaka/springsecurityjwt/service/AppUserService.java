package com.aquapaka.springsecurityjwt.service;

import com.aquapaka.springsecurityjwt.model.AppUser;
import com.aquapaka.springsecurityjwt.model.Role;
import com.aquapaka.springsecurityjwt.repository.AppUserRepository;
import com.aquapaka.springsecurityjwt.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class AppUserService {
    private final AppUserRepository appUserRepository;
    private final RoleRepository roleRepository;

    public AppUser saveAppUser(AppUser appUser) {
        return appUserRepository.save(appUser);
    }

    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    public void addRoleToAppUser(String username, String roleName) {
        AppUser appUser = appUserRepository.findByUsername(username);
        Role role = roleRepository.findByName(roleName);
        appUser.getRoles().add(role);
    }

    public AppUser getAppUser(String username) {
        log.info("Fetching user {}", username);
        return appUserRepository.findByUsername(username);
    }

    public List<AppUser> getAppUsers() {
        return appUserRepository.findAll();
    }
}