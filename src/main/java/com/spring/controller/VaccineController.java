package com.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class VaccineController {
	
	@GetMapping("/add-vaccine")
    public String addVaccine() {
    	return "vaccine/add-vaccine";
    }

}
