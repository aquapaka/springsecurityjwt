package com.aquapaka.springsecurityjwt.api;

import com.aquapaka.springsecurityjwt.model.AppUser;
import com.aquapaka.springsecurityjwt.service.AppUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AppUserResource {
    private final AppUserService appUserService;

    @GetMapping("/appUsers")
    public ResponseEntity<List<AppUser>> getAppUsers() {
        return ResponseEntity.ok().body(appUserService.getAppUsers());
    }
}
