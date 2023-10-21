package com.example.freelance_be;

import com.example.freelance_be.repositories.UserRepository;
import com.example.freelance_be.services.role.impl.RoleService;
import com.example.freelance_be.services.user.impl.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class FreelanceBeApplication {

	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(FreelanceBeApplication.class, args);

		RoleService roleService = applicationContext.getBean(RoleService.class);
		roleService.initRole();
		UserService userService = applicationContext.getBean(UserService.class);
		userService.initAdminAccount();

	}

}
