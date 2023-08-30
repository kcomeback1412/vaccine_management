package com.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class VaccinationSrsApplication {

//	@Bean
//	public PasswordEncoder getPasswordEncoder(){
//		return new BCryptPasswordEncoder();
//	}
	public static void main(String[] args) {
		SpringApplication.run(VaccinationSrsApplication.class, args);
	}

}
