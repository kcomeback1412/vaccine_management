package com.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.spring.consts.StatusEnum;
import com.spring.entities.VaccineType;
import com.spring.repositories.VaccineTypeRepository;

@Controller
public class VaccineTypeController {
	
	@Autowired
	private VaccineTypeRepository vaccineTypeRepository;
	
	@GetMapping("/vaccine-type-list")
    public String vaccineTypeList() {
    	return "vaccineType/vaccine-type-list";
    }

	@GetMapping("/addVaccineType")
    public String addVaccineTypeUI(Model model) {
		model.addAttribute("vaccineType", new VaccineType());
		
    	return "vaccineType/addVaccineType";
    }
	
	@PostMapping("/addVaccineType")
    public String createVaccineType(
    		@ModelAttribute("vaccineType") @Validated VaccineType vaccineType,
    		BindingResult result,
    		Model model) {
		
		if(result.hasErrors()) {
			return "vaccineType/addVaccineType";
		}
		
		vaccineType.setVaccineTypeStatus(StatusEnum.ACTIVE);
		
		vaccineTypeRepository.save(vaccineType);
		
    	return "redirect:/addVaccineType";
    }
	
	@GetMapping("/updateVaccineType")
    public String updateVaccineTypeUI() {
    	return "vaccineType/updateVaccineType";
    }
}
