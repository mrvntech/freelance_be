package com.example.freelance_be;

import com.example.freelance_be.dto.request.category.CreateCategoryRequestBody;
import com.example.freelance_be.entities.Category;
import com.example.freelance_be.repositories.CategoryRepository;
import com.example.freelance_be.repositories.UserRepository;
import com.example.freelance_be.services.category.impl.CategoryService;
import com.example.freelance_be.services.role.impl.RoleService;
import com.example.freelance_be.services.user.impl.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootApplication
public class FreelanceBeApplication {

	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(FreelanceBeApplication.class, args);

		RoleService roleService = applicationContext.getBean(RoleService.class);
		roleService.initRole();
		UserService userService = applicationContext.getBean(UserService.class);
		userService.initAdminAccount();
		CategoryRepository categoryRepository = applicationContext.getBean(CategoryRepository.class);
		Category be = new Category();
		be.setName("BE");
		Category fe = new Category();
		fe.setName("FE");
		categoryRepository.saveAll(List.of(be, fe));
	}

}
