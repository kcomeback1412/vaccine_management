package com.spring.controller;

import com.spring.consts.StatusEnum;
import com.spring.entities.InjectionSchedule;
import com.spring.entities.Vaccine;
import com.spring.entities.VaccineType;
import com.spring.repositories.InjectionScheduleRepository;
import com.spring.repositories.VaccineRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/injectionSchedule-management")
public class InjectionScheduleController {

	@Autowired
	private VaccineRepository vaccineRepository;
	
	@Autowired
	private InjectionScheduleRepository injectionScheduleRepository;

	@GetMapping("/list_injectionSchedule")
	public String injectionScheduleList(
			@RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
			@RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize,
			Model model) {
		model.addAttribute("option", pageSize);

		Sort sort = Sort.by("injectionScheduleId");

		PageRequest pageable = PageRequest.of(pageNum - 1, pageSize, sort);

		Page<InjectionSchedule> injectionSchedules = injectionScheduleRepository.findAll(pageable);

		model.addAttribute("injectionScheduleList", injectionSchedules);

		model.addAttribute("start", (pageNum - 1) * pageSize + 1);

		if (pageNum != injectionSchedules.getTotalPages()) {
			model.addAttribute("end", pageNum * pageSize);
		} else {
			model.addAttribute("end", injectionScheduleRepository.findAll().size());
		}

		model.addAttribute("total", injectionScheduleRepository.findAll().size());

		if (pageNum == 1) {
			model.addAttribute("prevStatus", "btn-link disabled");
		} else {
			model.addAttribute("prev", (pageNum - 1));
		}

		if (pageNum >= injectionSchedules.getTotalPages()) {
			model.addAttribute("nextStatus", "btn-link disabled");
		} else {
			model.addAttribute("next", (pageNum + 1));
		}

		return "injectionSchedule/list_injectionSchedule";
	}


	@GetMapping("/create_injectionSchedule")
	public String createInjectionScheduleUI(
			Model model
	){
		model.addAttribute("injectionSchedule", new InjectionSchedule());

		List<Vaccine> vaccineList = vaccineRepository.findAll();

		model.addAttribute("vaccineList", vaccineList);


		return "injectionSchedule/create_injectionSchedule";
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/create_injectionSchedule")
	public String saveInjectionSchedule(
			@ModelAttribute("injectionSchedule")  @Validated InjectionSchedule injectionSchedule,
			BindingResult result,
			Model model,
			RedirectAttributes redirectAttributes
	){

		if (result.hasErrors()) {
			return "injectionSchedule/create_injectionSchedule-vaccineType";
		}
		//1. validate name
		if (null != injectionScheduleRepository.checkInjectionScheduleId(injectionSchedule.getInjectionScheduleId())) {
			model.addAttribute("injectionScheduleIdMsg", "This code is already existed!");
			return "injectionSchedule/create_injectionSchedule";
		}

		//2. save
//		injectionSchedule.setUserCreate(SecurityContextHolder.getContext().getAuthentication().getName());
		injectionScheduleRepository.save(injectionSchedule);

		//3. list
		redirectAttributes.addFlashAttribute("msg", "Create Injection Schedule is successfully");
		return "redirect:/injectionSchedule-management/list_injectionSchedule";
	}

	@GetMapping("/update_injectionSchedule/{id}")
	public String updateInjectionScheduleUI(@PathVariable String id, Model model) {

		model.addAttribute("injectionSchedule", new InjectionSchedule());

		model.addAttribute("injectionScheduleId", injectionScheduleRepository.findById(id).orElse(null).getInjectionScheduleId());

//		model.addAttribute("vaccineName", vaccineRepository.findById(id).orElse(null).getVaccineName());

		model.addAttribute("startDate", injectionScheduleRepository.findById(id).orElse(null).getStartDate());

		model.addAttribute("endDate", injectionScheduleRepository.findById(id).orElse(null).getEndDate());

		model.addAttribute("place", injectionScheduleRepository.findById(id).orElse(null).getPlace());

		model.addAttribute("description", injectionScheduleRepository.findById(id).orElse(null).getDescription());

		List<Vaccine> vaccineList = vaccineRepository.findAll();
		model.addAttribute("vaccineList", vaccineList);
		model.addAttribute("injectionSchedule", injectionScheduleRepository.findById(id).orElse(null));

		return "injectionSchedule/update_injectionSchedule";
	}

//	@PostMapping("/update_injectionSchedule")
//	public String updateInjectionSchedule(HttpServletRequest httpServletRequest) {
//		try {
//			if (httpServletRequest.getParameterValues("id") != null) {
//				for (String id : httpServletRequest.getParameterValues("id")) {
//					InjectionSchedule injectionSchedule = injectionScheduleRepository.getById(id);
//					injectionScheduleRepository.save(injectionSchedule);
//				}
//			}
//			return "redirect:/injectionSchedule-management/list_injectionSchedule";
//		} catch (Exception e) {
//			return "injectionSchedule/list_injectionSchedule";
//		}
//	}
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/update_injectionSchedule")
	public String updateInjectionSchedule(
			@ModelAttribute("injectionSchedule") @Validated InjectionSchedule injectionSchedule,
			BindingResult result,
			@ModelAttribute("id") String id,
			@RequestParam(name = "pageNum", required=false, defaultValue = "1") Integer pageNum,
			Model model,
			RedirectAttributes redirectAttributes
	) {
		if (result.hasErrors()) {
			model.addAttribute("injectionScheduleId", injectionScheduleRepository.findById(id).orElse(null).getInjectionScheduleId());
			return "injectionSchedule/update_injectionSchedule";
		}

		injectionScheduleRepository.save(injectionSchedule);
		redirectAttributes.addFlashAttribute("msg", "Edit Injection Schedule is successfully");
		return "redirect:/injectionSchedule-management/list_injectionSchedule";
	}

	//	@PreAuthorize("hasRole('ADMIN')")
//	@GetMapping("/delete/{id}")
//	public String deleteInjectionSchedule(@PathVariable String id, RedirectAttributes attributes) {
//		injectionScheduleRepository.deleteById(id);
//		attributes.addFlashAttribute("msg", "Delete Injection Schedule is successfully");
//		return "redirect:/list_injectionSchedule";
//	}
}
