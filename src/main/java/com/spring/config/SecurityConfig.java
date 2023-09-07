package com.spring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.spring.auth.CustomUserDetailService;
import com.spring.consts.RoleEnum;
import com.spring.entities.Users;
import com.spring.entities.UserDetail;
import com.spring.repositories.UserDetailRepository;
import com.spring.repositories.UsersRepository;

@Configuration
public class SecurityConfig {
	
	@Autowired
	CustomUserDetailService userDetailService;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	UsersRepository usersRepository;
	
	@Autowired
	UserDetailRepository userDetailRepository;
	
	@Autowired
	public void configGlobal(AuthenticationManagerBuilder builder) throws Exception {
		builder.userDetailsService(userDetailService).passwordEncoder(passwordEncoder);
		
		builder.userDetailsService(userDetailService);
	}
	
	private final static String[] permitAllLink = {
			"",
			"/",
			"/index",
			"/home",
			"/dashboard",
    		"/login",
			"/logout",
    		"/js/**",
    		"/css/**",
    		"/img/**",
    		"/info"
    }; 
	
	private final static String[] permitEmployeeLink = {

    };
	
	private final static String[] permitCustomerLink = {
			
    };
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
		
		httpSecurity.authorizeHttpRequests(auth -> {
			auth.requestMatchers(permitAllLink).permitAll();
			
			auth.requestMatchers("/**").hasAuthority(RoleEnum.ADMIN.name());

			auth.requestMatchers(permitEmployeeLink).hasAuthority(RoleEnum.EMPLOYEE.name());
			
			auth.requestMatchers(permitCustomerLink).hasAnyAuthority(RoleEnum.EMPLOYEE.name(), RoleEnum.CUSTOMER.name());
		}).formLogin(form -> {
				form.loginPage("/login")
					.loginProcessingUrl("/login-check")
					.usernameParameter("userName")
					.passwordParameter("password")
					.defaultSuccessUrl("/home")
					.permitAll();
		});
		
		return httpSecurity.build();
	}
	
	@Bean
	public void createAdmin() {
		if(usersRepository.findByUserName("admin") == null) {
		
			Users admin = new Users();
		
			admin.setUserName("admin");
			admin.setPassword(passwordEncoder.encode("admin"));
			admin.setRoleEnum(RoleEnum.ADMIN);
		
			usersRepository.save(admin);
			
			UserDetail adminDetail = new UserDetail();
			
			userDetailRepository.save(adminDetail);
		}
	}
	
}
