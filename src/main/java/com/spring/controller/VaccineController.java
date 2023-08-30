package com.spring.controller;

import com.spring.entities.Vaccine;
import com.spring.repositories.VaccineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class VaccineController {
    @Autowired
    VaccineRepository vaccineRepository;

    @GetMapping("/vaccine_list")
    public String VaccineList(Model model) {
        List<Vaccine> vaccines = vaccineRepository.findAll();
        model.addAttribute("vaccineList", vaccines);
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
