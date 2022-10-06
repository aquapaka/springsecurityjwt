package com.aquapaka.springsecurityjwt.service;

import com.aquapaka.springsecurityjwt.model.AppUser;
import com.aquapaka.springsecurityjwt.model.Role;
import com.aquapaka.springsecurityjwt.repository.AppUserRepository;
import com.aquapaka.springsecurityjwt.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class AppUserService implements UserDetailsService {
    private final AppUserRepository appUserRepository;
    private final RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = appUserRepository.findByUsername(username);

        if (appUser == null) {
            log.error("User not found in the database");
            throw new UsernameNotFoundException("user not found in the database");
        } else {
            log.error("User found in the database: {}", username);
        }

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        appUser.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });

        return new User(appUser.getUsername(), appUser.getPassword(), authorities);
    }

    public AppUser saveAppUser(AppUser appUser) {
        log.info("Saving new user {} to the database", appUser.getName());
        return appUserRepository.save(appUser);
    }

    public Role saveRole(Role role) {
        log.info("Saving new role {} to the database", role.getName());
        return roleRepository.save(role);
    }

    public void addRoleToAppUser(String username, String roleName) {
        log.info("Adding role {} to user {}", roleName, username);
        AppUser appUser = appUserRepository.findByUsername(username);
        Role role = roleRepository.findByName(roleName);
        appUser.getRoles().add(role);
    }

    public AppUser getAppUser(String username) {
        log.info("Fetching user {}", username);
        return appUserRepository.findByUsername(username);
    }

    public List<AppUser> getAppUsers() {
        log.info("Fetching all users");
        return appUserRepository.findAll();
    }

}
