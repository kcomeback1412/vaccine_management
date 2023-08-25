package com.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String addVaccineType(
    		@ModelAttribute("vaccineType") @Validated VaccineType vaccineType,
    		BindingResult result,
    		Model model) {
		
		if(result.hasErrors()) {
			return "vaccineType/addVaccineType";
		}
		
		if(null != vaccineTypeRepository.checkVaccineTypeId(vaccineType.getVaccineTypeId())) {
			model.addAttribute("vaccineTypeIdMsg", "This code is already existed!");
			return "vaccineType/addVaccineType";
		}
		
		vaccineType.setVaccineTypeStatus(StatusEnum.ACTIVE);
		
		vaccineTypeRepository.save(vaccineType);
		
    	return "redirect:/addVaccineType";
    }
	
	@GetMapping("/updateVaccineType/{id}")
    public String updateVaccineTypeUI(
    		@PathVariable String id, 
    		Model model) {
		
		model.addAttribute("vaccineType", vaccineTypeRepository.findById(id));
		
		if(vaccineTypeRepository.findById(id).orElse(null).getVaccineTypeStatus() == StatusEnum.ACTIVE) {
			
		}
		
    	return "vaccineType/updateVaccineType";
    }
	
	@GetMapping("/updateVaccineType")
    public String updateVaccineType(Model model) {
		
		model.addAttribute("vaccineType", new VaccineType());
		
    	return "vaccineType/updateVaccineType";
    }
	
	@PostMapping("/updateVaccineType")
    public String updateVaccineType(
    		@ModelAttribute("vaccineType") @Validated VaccineType vaccineType,
    		BindingResult result,
    		@RequestParam("activeCheckBox") boolean active,
    		Model model) {
		
		if(result.hasErrors()) {
			return "vaccineType/updateVaccineType";
		}
		
		if(active) 
			vaccineType.setVaccineTypeStatus(StatusEnum.ACTIVE);
		else 
			vaccineType.setVaccineTypeStatus(StatusEnum.IN_ACTIVE);
		
		vaccineTypeRepository.save(vaccineType);
		
    	return "redirect:/updateVaccineType";
    }
}
