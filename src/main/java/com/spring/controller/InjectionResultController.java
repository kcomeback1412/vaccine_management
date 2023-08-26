package com.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.spring.repositories.InjectionResultRepository;

@Controller
public class InjectionResultController {
	
	@Autowired
	private InjectionResultRepository injectionResultRepository;
	
	@GetMapping("/addInjectionResult")
    public String addInjectionResultUI(Model model) {			
    	return "injectionResult/addInjectionResult";
    }
	
	@GetMapping("/updateInjectionResult")
    public String updateInjectionResultUI(Model model) {			
    	return "injectionResult/updateInjectionResult";
    }
}
