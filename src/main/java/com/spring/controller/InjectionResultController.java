package com.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.spring.repositories.InjectionResultRepository;

@Controller
public class InjectionResultController {
	
	@Autowired
	private InjectionResultRepository injectionResultRepository;
	
	@GetMapping("/addInjectionResult")
    public String addInjectionResultUI() {		
    	return "injectionResult/addInjectionResult";
    }
}
