package com.spring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
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
    		"/login",
    		"/js/**",
    		"/css/**",
    		"/img/**",
			"/logout",
			"/api/v1/**"

    };

	private final static String[] permitAdminLink = {
			"/employee-management/**"
	};
	
	private final static String[] permitEmployeeLink = {
			"/customer-manage/**",
			"/vaccine-management/**",
			"/vaccineType-management/**",
			"/injectionSchedule-management/create_injectionSchedule",
			"/injection-result-management/add-injection-result",
			"/news-management/create_news",
			"/report_injection_result"
    };

	
	private final static String[] permitCustomerLink = {
			"",
			"/",
			"/index",
			"/home",
			"/dashboard",
			"/injectionSchedule-management/list_injectionSchedule",
			"/injection-result-management/injection_result-list",
			"/news-management/news_list"
    };
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
		
		httpSecurity.authorizeHttpRequests(auth -> {
			auth.requestMatchers(permitAllLink).permitAll()
				.requestMatchers(permitAdminLink).hasAuthority(RoleEnum.ADMIN.name())
				.requestMatchers(permitEmployeeLink).hasAnyAuthority(RoleEnum.EMPLOYEE.name(), RoleEnum.ADMIN.name())
				.requestMatchers(permitCustomerLink).hasAnyAuthority(RoleEnum.CUSTOMER.name(), RoleEnum.EMPLOYEE.name(),RoleEnum.ADMIN.name())
				.anyRequest().denyAll();
		}).formLogin(form -> {
				form.loginPage("/login")
					.loginProcessingUrl("/login-check")
					.usernameParameter("userName")
					.passwordParameter("password")
					.defaultSuccessUrl("/home")
					.permitAll();
		}).csrf().disable();
		
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
