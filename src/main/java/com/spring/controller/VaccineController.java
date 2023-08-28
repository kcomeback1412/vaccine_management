package com.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class VaccineController {
    @GetMapping("/vaccine_list")
    public String VaccineList() {
        return "Vaccine/vaccine_list";
    }

	@GetMapping("/add-vaccine")
    public String addVaccine() {
    	return "Vaccine/add-vaccine";
    }
	
	@GetMapping("/update-vaccine")
    public String updateVaccine() {
    	return "Vaccine/update-vaccine";
    }

    @GetMapping("/import_vaccine")
    public String ImportVaccine() {
        return "Vaccine/import_vaccine";
    }
	

}
