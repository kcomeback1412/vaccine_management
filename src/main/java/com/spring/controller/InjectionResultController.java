package com.spring.controller;

import com.spring.consts.RoleEnum;
import com.spring.entities.*;
import com.spring.service.PlaceService;
import com.spring.service.PreventionService;
import com.spring.service.UserDetailsService;
import com.spring.service.VaccineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.spring.repositories.InjectionResultRepository;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/injection-result-management")
public class InjectionResultController {
	
	@Autowired
	private InjectionResultRepository injectionResultRepository;

	@Autowired
	UserDetailsService userDetailsService;

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
	
	@PostMapping("/add-injection-result")
    public String addInjectionResult(
    		@ModelAttribute("injectionResult") @Validated InjectionResult injectionResult,
			@RequestParam("idCusInput") Integer idCustomer,
    		BindingResult result,
    		Model model,
			RedirectAttributes redirectAttributes
	) {
		UserDetail userDetail = userDetailsService.findByIdReturnUserDetail(idCustomer);

		if((userDetail == null) ||  userDetail.getUsers2().getRoleEnum() != RoleEnum.CUSTOMER) {
			redirectAttributes.addFlashAttribute("injectionResult", injectionResult);
			redirectAttributes.addFlashAttribute("msgError", "ERROR");
			return  "redirect:/injection-result-management/add-injection-result";
		} else {
			injectionResult.setUsers3(userDetail.getUsers2());
			injectionResultRepository.save(injectionResult);
			return "redirect:/injection-result-management/vaccine_result_list";
		}
    }
	
	@GetMapping("/updateInjectionResult")
    public String updateInjectionResultUI(Model model) {			
    	return "injectionResult/updateInjectionResult";
    }
}
