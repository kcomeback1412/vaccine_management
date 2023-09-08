package com.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.consts.StatusEnum;
import com.spring.entities.VaccineType;
import com.spring.repositories.VaccineTypeRepository;
import com.spring.service.impl.VaccineTypeServiceImpl;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/vaccineType-management")
public class VaccineTypeController {

	@Autowired
	private VaccineTypeRepository vaccineTypeRepository;
	
	@Autowired
	private VaccineTypeServiceImpl vaccineTypeServiceImpl;

	@GetMapping("/vaccineType-list")
	public String vaccineTypeListUI(
			@RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
			@RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize,
			@RequestParam(name = "nameForSearch",required = false) String nameForSearch,
			Model model) {

		model.addAttribute("option", pageSize);

		Sort sort = Sort.by("vaccineTypeId");

		PageRequest pageable = PageRequest.of(pageNum - 1, pageSize, sort);

		Page<VaccineType> vaccineTypeList;
		
		if(nameForSearch == null || nameForSearch.isEmpty()) {
			vaccineTypeList = vaccineTypeRepository.findAll(pageable);
        } else  {
        	vaccineTypeList = vaccineTypeRepository.findPageByNameLike(nameForSearch, pageable);
            model.addAttribute("nameForSearch", nameForSearch);
        }

		model.addAttribute("vaccineTypeList", vaccineTypeList);

		model.addAttribute("start", (pageNum - 1) * pageSize + 1);

		if (pageNum != vaccineTypeList.getTotalPages()) {
			model.addAttribute("end", pageNum * pageSize);
		} else {
			model.addAttribute("end", vaccineTypeList.getTotalElements());
		}

		model.addAttribute("total", vaccineTypeList.getTotalElements());

		if (pageNum == 1) {
			model.addAttribute("prevStatus", "btn-link disabled");
		} else {
			model.addAttribute("prev", (pageNum - 1));
		}

		if (pageNum >= vaccineTypeList.getTotalPages()) {
			model.addAttribute("nextStatus", "btn-link disabled");
		} else {
			model.addAttribute("next", (pageNum + 1));
		}

		return "vaccineType/vaccineType-list";
	}

	@PostMapping("/update-vaccineType-list")
	public String updateCustomerUI(HttpServletRequest httpServletRequest) {
		try {
			if (httpServletRequest.getParameterValues("id") != null) {
				for (String id : httpServletRequest.getParameterValues("id")) {
					VaccineType vaccineType = vaccineTypeServiceImpl.getById(id);
					vaccineType.setVaccineTypeStatus(StatusEnum.IN_ACTIVE);
					vaccineTypeServiceImpl.save(vaccineType);
				}
			}
			return "redirect:/vaccineType-management/vaccineType-list";
		} catch (Exception e) {
			return "vaccineType/vaccineType-list";
		}
	}

	@GetMapping("/create-vaccineType")
	public String addVaccineTypeUI(Model model) {
		model.addAttribute("vaccineType", new VaccineType());

		return "vaccineType/create-vaccineType";
	}

	@PostMapping("/create-vaccineType")
	public String addVaccineType(
			@ModelAttribute("vaccineType") @Validated VaccineType vaccineType,
			BindingResult result, 
			Model model) {

		if (result.hasErrors()) {
			return "vaccineType/create-vaccineType";
		}

		if (null != vaccineTypeServiceImpl.checkVaccineTypeId(vaccineType.getVaccineTypeId())) {
			model.addAttribute("vaccineTypeIdMsg", "This code is already existed!");
			return "vaccineType/create-vaccineType";
		}

		vaccineType.setVaccineTypeStatus(StatusEnum.ACTIVE);

		vaccineTypeServiceImpl.save(vaccineType);

		return "redirect:/vaccineType-management/vaccineType-list";
	}

	@GetMapping("/update-vaccineType/{id}")
	public String updateVaccineTypeUI(@PathVariable String id, Model model) {

		model.addAttribute("vaccineType", vaccineTypeServiceImpl.findById(id));

		model.addAttribute("vaccineTypeId", vaccineTypeServiceImpl.findById(id).orElse(null).getVaccineTypeId());

		model.addAttribute("vaccineTypeName", vaccineTypeServiceImpl.findById(id).orElse(null).getVaccineTypeName());

		model.addAttribute("description", vaccineTypeServiceImpl.findById(id).orElse(null).getDescription());

		if (vaccineTypeRepository.findById(id).orElse(null).getVaccineTypeStatus() == StatusEnum.ACTIVE) {

			model.addAttribute("checked", "checked");
		}

		return "vaccineType/update-vaccineType";
	}

	@PostMapping("/update-vaccineType")
	public String updateVaccineType(
			@ModelAttribute("vaccineType") @Validated VaccineType vaccineType,
			BindingResult result, 
			@RequestParam(name = "vaccineTypeId", required = false) String id,
			@RequestParam(name = "activeCheckBox", required = false) boolean active,
			RedirectAttributes redirectAttributes, 
			Model model) {

		if (result.hasErrors()) {
			model.addAttribute("vaccineTypeId", vaccineTypeServiceImpl.findById(id).orElse(null).getVaccineTypeId());
			return "vaccineType/update-vaccineType";
		}

		if (active)
			vaccineType.setVaccineTypeStatus(StatusEnum.ACTIVE);
		else
			vaccineType.setVaccineTypeStatus(StatusEnum.IN_ACTIVE);

		vaccineTypeRepository.save(vaccineType);

		return "redirect:/vaccineType-management/vaccineType-list";
	}
}
