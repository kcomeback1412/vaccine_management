package com.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.spring.entities.InjectionResult;
import com.spring.repositories.InjectionResultRepository;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/injection-result-management")
public class InjectionResultController {
	
	@Autowired
	private InjectionResultRepository injectionResultRepository;


	@GetMapping("/vaccine_result_list")
	public String VaccineResultList() {
		return "vaccine/vaccine_result_list";
	}

	@GetMapping("/add-injection-result")
    public String addInjectionResultUI(Model model) {	
		
		model.addAttribute("injectionResult", new InjectionResult());
		
    	return "injectionResult/addInjectionResult";
    }
	
	@PostMapping("/addInjectionResult")
    public String addInjectionResult(
    		@ModelAttribute("injectionResult") @Validated InjectionResult injectionResult,
    		BindingResult result,
    		Model model) {	
		
		model.addAttribute("injectionResult", new InjectionResult());
		
    	return "injectionResult/addInjectionResult";
    }
	
	@GetMapping("/updateInjectionResult")
    public String updateInjectionResultUI(Model model) {			
    	return "injectionResult/updateInjectionResult";
    }
}
