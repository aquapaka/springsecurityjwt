package com.aquapaka.springsecurityjwt;

import com.aquapaka.springsecurityjwt.model.AppUser;
import com.aquapaka.springsecurityjwt.model.Role;
import com.aquapaka.springsecurityjwt.service.AppUserService;
import org.apache.catalina.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;

@SpringBootApplication
public class SpringsecurityjwtApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringsecurityjwtApplication.class, args);
	}

	CommandLineRunner run(AppUserService appUserService) {
		return args -> {
			appUserService.saveRole(new Role(null, "ROLE_USER"));
			appUserService.saveRole(new Role(null, "ROLE_MANAGER"));
			appUserService.saveRole(new Role(null, "ROLE_ADMIN"));

			appUserService.saveAppUser(new AppUser(null, "Tam Long", "aquapaka", "12345", new ArrayList<>()));
			appUserService.saveAppUser(new AppUser(null, "Meimei", "meimei", "12345", new ArrayList<>()));
			appUserService.saveAppUser(new AppUser(null, "Pizza", "pizza", "12345", new ArrayList<>()));
			appUserService.saveAppUser(new AppUser(null, "Wind", "wind", "12345", new ArrayList<>()));

			appUserService.addRoleToAppUser("aquapaka", "ROLE_USER");
			appUserService.addRoleToAppUser("aquapaka", "ROLE_ADMIN");
			appUserService.addRoleToAppUser("meimei", "ROLE_USER");
		};
	}

}
