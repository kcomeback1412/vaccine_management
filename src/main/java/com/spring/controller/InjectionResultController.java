package com.spring.controller;

import com.spring.entities.Place;
import com.spring.entities.Prevention;
import com.spring.entities.Vaccine;
import com.spring.service.PlaceService;
import com.spring.service.PreventionService;
import com.spring.service.VaccineService;
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

import java.util.List;

@Controller
@RequestMapping("/injection-result-management")
public class InjectionResultController {
	
	@Autowired
	private InjectionResultRepository injectionResultRepository;

	@Autowired
	VaccineService vaccineService;

	@Autowired
	PlaceService placeService;

	@Autowired
	PreventionService preventionService;

	@GetMapping("/vaccine_result_list")
	public String VaccineResultList() {
		return "vaccine/vaccine_result_list";
	}

	@GetMapping("/add-injection-result")
    public String addInjectionResultUI(
		Model model
	) {
		List<Vaccine> vaccineList = vaccineService.findAllVaccine();
		List<Place> placeList = placeService.findAllPlace();
		List<Prevention> preventionList = preventionService.findAllPrevention();

		model.addAttribute("vaccineList",vaccineList);
		model.addAttribute("placeList",placeList);
		model.addAttribute("preventionList",preventionList);
		
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
