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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.consts.StatusEnum;
import com.spring.entities.UserDetail;
import com.spring.entities.VaccineType;
import com.spring.repositories.VaccineTypeRepository;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class VaccineTypeController {

	@Autowired
	private VaccineTypeRepository vaccineTypeRepository;

	@GetMapping("/vaccine-type-list")
	public String vaccineTypeListUI(
			@RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
			@RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize, Model model) {

		model.addAttribute("option", pageSize);

		Sort sort = Sort.by("vaccineTypeId");

		PageRequest pageable = PageRequest.of(pageNum - 1, pageSize, sort);

		Page<VaccineType> vaccineTypeList = vaccineTypeRepository.findAll(pageable);

		model.addAttribute("vaccineTypeList", vaccineTypeList);

		model.addAttribute("start", (pageNum - 1) * pageSize + 1);

		if (pageNum != vaccineTypeList.getTotalPages()) {
			model.addAttribute("end", pageNum * pageSize);
		} else {
			model.addAttribute("end", vaccineTypeRepository.findAll().size());
		}

		model.addAttribute("total", vaccineTypeRepository.findAll().size());

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

		return "vaccineType/vaccine-type-list";
	}

	@PostMapping("/update-vaccine-type-list")
	public String updateCustomerUI(HttpServletRequest httpServletRequest) {
		try {
			if (httpServletRequest.getParameterValues("id") != null) {
				for (String id : httpServletRequest.getParameterValues("id")) {
					VaccineType vaccineType = vaccineTypeRepository.getById(id);
					vaccineType.setVaccineTypeStatus(StatusEnum.IN_ACTIVE);
					vaccineTypeRepository.save(vaccineType);
				}
			}
			return "redirect:/vaccine-type-list";
		} catch (Exception e) {
			return "vaccineType/vaccine-type-list";
		}
	}

	@GetMapping("/addVaccineType")
	public String addVaccineTypeUI(Model model) {
		model.addAttribute("vaccineType", new VaccineType());

		return "vaccineType/addVaccineType";
	}

	@PostMapping("/addVaccineType")
	public String addVaccineType(@ModelAttribute("vaccineType") @Validated VaccineType vaccineType,
			BindingResult result, Model model) {

		if (result.hasErrors()) {
			return "vaccineType/addVaccineType";
		}

		if (null != vaccineTypeRepository.checkVaccineTypeId(vaccineType.getVaccineTypeId())) {
			model.addAttribute("vaccineTypeIdMsg", "This code is already existed!");
			return "vaccineType/addVaccineType";
		}

		vaccineType.setVaccineTypeStatus(StatusEnum.ACTIVE);

		vaccineTypeRepository.save(vaccineType);

		return "redirect:/vaccine-type-list";
	}

	@GetMapping("/updateVaccineType/{id}")
	public String updateVaccineTypeUI(@PathVariable String id, Model model) {

		model.addAttribute("vaccineType", vaccineTypeRepository.findById(id));

		model.addAttribute("vaccineTypeId", vaccineTypeRepository.findById(id).orElse(null).getVaccineTypeId());

		model.addAttribute("vaccineTypeName", vaccineTypeRepository.findById(id).orElse(null).getVaccineTypeName());

		model.addAttribute("description", vaccineTypeRepository.findById(id).orElse(null).getDescription());

		if (vaccineTypeRepository.findById(id).orElse(null).getVaccineTypeStatus() == StatusEnum.ACTIVE) {

			model.addAttribute("checked", "checked");
		}

		return "vaccineType/updateVaccineType";
	}

	@PostMapping("/updateVaccineType")
	public String updateVaccineType(@ModelAttribute("vaccineType") @Validated VaccineType vaccineType,
			BindingResult result, @RequestParam(name = "vaccineTypeId", required = false) String id,
			@RequestParam(name = "activeCheckBox", required = false) boolean active,
			RedirectAttributes redirectAttributes, Model model) {

		if (result.hasErrors()) {
			model.addAttribute("vaccineTypeId", vaccineTypeRepository.findById(id).orElse(null).getVaccineTypeId());
			return "vaccineType/updateVaccineType";
		}

		if (active)
			vaccineType.setVaccineTypeStatus(StatusEnum.ACTIVE);
		else
			vaccineType.setVaccineTypeStatus(StatusEnum.IN_ACTIVE);

		vaccineTypeRepository.save(vaccineType);

		return "redirect:/vaccine-type-list";
	}
}
