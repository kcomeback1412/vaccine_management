package com.spring.controller;

import com.spring.consts.RoleEnum;
import com.spring.entities.*;
import com.spring.repositories.UsersRepository;
import com.spring.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
	InjectionResultService injectionResultService;

	@Autowired
	UserDetailsService userDetailsService;

	@Autowired
	VaccineService vaccineService;

	@Autowired
	PlaceService placeService;

	@Autowired
	PreventionService preventionService;

	@Autowired
	UsersRepository usersRepository;


	@GetMapping("/add-injection-result")
    public String addInjectionResultUI(
		Model model
	) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		model.addAttribute("userName", authentication.getName());
		model.addAttribute("userRole", authentication.getAuthorities().toString());



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
			BindingResult bindingResult,
			@RequestParam("idCusInput") Integer idCustomer,
    		Model model,
			RedirectAttributes redirectAttributes
	) {
		UserDetail userDetail = userDetailsService.findByIdReturnUserDetail(idCustomer);


		if((userDetail == null) ||  userDetail.getUsers2().getRoleEnum() != RoleEnum.CUSTOMER
				|| bindingResult.hasErrors()
			) {
			redirectAttributes.addFlashAttribute("msgError", "Not found customer with id = " + idCustomer);
			return  "redirect:/injection-result-management/add-injection-result";
		} else {
			injectionResult.setUsers3(userDetail.getUsers2());
			injectionResultService.save(injectionResult);
			return "redirect:/injection-result-management/injection_result-list";
		}
    }

	@GetMapping("/injection_result-list")
	public String VaccineResultList(
			@RequestParam(name = "pageNum", defaultValue = "1") Integer pageNum,
			@RequestParam(name = "pageSize", defaultValue = "5") Integer pageSize,
			@RequestParam(name = "preventionNameForSearch",required = false) String preventionNameForSearch,
			Model model
	) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		model.addAttribute("userName", authentication.getName());
		model.addAttribute("userRole", authentication.getAuthorities().toString());
		Users userLogged = usersRepository.findByUserName(authentication.getName());

		Integer totalInjectResult = injectionResultService.countAllInjectionResult();
		model.addAttribute("totalInjectResult", totalInjectResult);
		model.addAttribute("pageSize", pageSize);

		Pageable pageable = PageRequest.of(pageNum - 1, pageSize);

		List<InjectionResult> injectionResultList;
		if(userLogged.getRoleEnum() != RoleEnum.CUSTOMER) {
			if(preventionNameForSearch == null || preventionNameForSearch.isEmpty()) {
				injectionResultList = injectionResultService.findAll();
			} else {
				injectionResultList = injectionResultService.findAllByPreventionNameLike(preventionNameForSearch);
				model.addAttribute("preventionNameForSearch", preventionNameForSearch);
				model.addAttribute("totalInjectResult", injectionResultList.size());
			}
		} else {
			if(preventionNameForSearch == null || preventionNameForSearch.isEmpty()) {
				injectionResultList = injectionResultService.findAllByUsers3(userLogged);
			} else {
				injectionResultList = injectionResultService.findAllByUsers3PreventionNameLike(userLogged,preventionNameForSearch);
				model.addAttribute("preventionNameForSearch", preventionNameForSearch);
				model.addAttribute("totalInjectResult", injectionResultList.size());
			}
		}


		Page<InjectionResult> injectionResults;
		if(injectionResultList != null) {
			injectionResults = injectionResultService.convertListInjectionResultToPageInjectionResult(pageable, injectionResultList);
		} else {
			injectionResults = null;
		}

		model.addAttribute("currentPage", pageNum);
		if(injectionResults != null) {
			model.addAttribute("injectionResults", injectionResults);
		}

		return "injectionResult/injection_result_list";
	}

//	DELETE
	@PostMapping(value = "/delete-update-injection_result", params = "delete")
	public String deleteInjectionResult(
			@RequestParam(value = "listId", required = false) List<String> listId,
			RedirectAttributes redirectAttributes
	){
		if(listId != null) {
			injectionResultService.deleteInjectionResultByListId(listId);
			redirectAttributes.
					addFlashAttribute("msgSuccess", "Delete injection result success!");
		} else {
			redirectAttributes.
					addFlashAttribute("msgError", "You must select injection result to delete");
		}
		return "redirect:/injection-result-management/injection_result-list";
	}


//	Update UI
	@PostMapping(value = "/delete-update-injection_result", params = "update")
    public String updateInjectionResultUI(
			@RequestParam(value = "listId", required = false) List<String> listId,
		  	RedirectAttributes redirectAttributes,
		  	Model model
	) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		model.addAttribute("userName", authentication.getName());
		model.addAttribute("userRole", authentication.getAuthorities().toString());

		List<Vaccine> vaccineList = vaccineService.findAllVaccine();
		List<Place> placeList = placeService.findAllPlace();
		List<Prevention> preventionList = preventionService.findAllPrevention();

		model.addAttribute("vaccineList",vaccineList);
		model.addAttribute("placeList",placeList);
		model.addAttribute("preventionList",preventionList);

		if((listId != null) && (listId.size() > 1)) {
			redirectAttributes.
					addFlashAttribute("msgError", "You can only select 1 injection result to update");
			return "redirect:/injection-result-management/injection_result-list";
		} else if ((listId != null) && (listId.size() == 1)) {
			model.addAttribute("injectionResultInfo"
					, injectionResultService.findByID(listId.get(0)).orElse(null));
			return "injectionResult/update-injection_result";
		} else {
			redirectAttributes.
					addFlashAttribute("msgError", "You must select injection result to update");
			return "redirect:/injection-result-management/injection_result-list";
		}

    }

	@PostMapping(value = "/delete-update-injection_result", params = "update-save")
	public String updateInjectionResult(
			@ModelAttribute("injectionResultUpdate") InjectionResult injectionResult,
			RedirectAttributes redirectAttributes,
			Model model
	){

		injectionResultService.save(injectionResult);
		redirectAttributes.addFlashAttribute("msgSuccess", "Update Injection result success");
		return "redirect:/injection-result-management/injection_result-list";
	}
}
