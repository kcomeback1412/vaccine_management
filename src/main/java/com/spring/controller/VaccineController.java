package com.spring.controller;

import com.spring.consts.StatusEnum;
import com.spring.entities.Vaccine;
import com.spring.entities.VaccineType;
import com.spring.repositories.VaccineRepository;
import com.spring.repositories.VaccineTypeRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class VaccineController {
    @Autowired
    VaccineRepository vaccineRepository;

    @Autowired
    VaccineTypeRepository vaccineTypeRepository;

    // LIST
    @GetMapping("/vaccine_list")
    public String VaccineList(
            @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize,
            Model model) {
        model.addAttribute("option", pageSize);

        Sort sort = Sort.by("vaccineId");

        PageRequest pageable = PageRequest.of(pageNum - 1, pageSize, sort);

        Page<Vaccine> vaccines = vaccineRepository.findAll(pageable);

        model.addAttribute("vaccineList", vaccines);

        model.addAttribute("start", (pageNum - 1) * pageSize + 1);

        if (pageNum != vaccines.getTotalPages()) {
            model.addAttribute("end", pageNum * pageSize);
        } else {
            model.addAttribute("end", vaccineRepository.findAll().size());
        }

        model.addAttribute("total", vaccineRepository.findAll().size());

        if (pageNum == 1) {
            model.addAttribute("prevStatus", "btn-link disabled");
        } else {
            model.addAttribute("prev", (pageNum - 1));
        }

        if (pageNum >= vaccines.getTotalPages()) {
            model.addAttribute("nextStatus", "btn-link disabled");
        } else {
            model.addAttribute("next", (pageNum + 1));
        }

        return "vaccine/vaccine_list";
    }


    // ADD
	@GetMapping("/add-vaccine")
    public String addVaccineUI(Model model) {
        model.addAttribute("vaccine", new Vaccine());

        List<VaccineType> vaccineTypes = vaccineTypeRepository.findAll();
        model.addAttribute("vaccineTypes", vaccineTypes);

        return "vaccine/add-vaccine";
    }

    @PostMapping("/add-vaccine")
    public String addVaccine(
            @ModelAttribute("vaccine") @Validated Vaccine vaccine,
            BindingResult result,
            Model model) {
       if (result.hasErrors()) {
           System.out.println(1);
            return "vaccine/add-vaccine";
        }

       if (null != vaccineRepository.checkVaccineId(vaccine.getVaccineId())) {
            model.addAttribute("vaccineIdMsg", "This code is already existed!");
           System.out.println(2);
            return "vaccine/add-vaccine";
       }

        vaccine.setVaccineStatus(StatusEnum.ACTIVE);

        vaccineRepository.save(vaccine);


        System.out.println(3);
        return "redirect:/vaccine_list";
    }

    // UPDATE
	@GetMapping("/update-vaccine/{id}")
    public String updateVaccineUI(
            @PathVariable String id,
            Model model) {
        List<VaccineType> vaccineTypes = vaccineTypeRepository.findAll();
        model.addAttribute("vaccineTypes", vaccineTypes);
        model.addAttribute("vaccine", vaccineRepository.findById(id).orElse(null));

        if (vaccineRepository.findById(id).orElse(null).getVaccineStatus() == StatusEnum.ACTIVE) {

            model.addAttribute("checked", "checked");
        }

        return "vaccine/update-vaccine";
    }

    @PostMapping("/update-vaccine-list")
    public String updateVaccine(HttpServletRequest httpServletRequest) {
        try {
            if (httpServletRequest.getParameterValues("id") != null) {
                for (String id : httpServletRequest.getParameterValues("id")) {
                    Vaccine vaccine = vaccineRepository.getById(id);
                    vaccine.setVaccineStatus(StatusEnum.IN_ACTIVE);
                    vaccineRepository.save(vaccine);
                }
            }
            return "redirect:/vaccine_list";
        } catch (Exception e) {
            return "vaccine/vaccine_list";
        }
    }

    @PostMapping("/update-vaccine")
    public String updateVaccine(
            @ModelAttribute("vaccine") @Validated Vaccine vaccine,
            BindingResult result,
            @RequestParam(name = "vaccineId", required = false) String id,
            @RequestParam(name = "activeCheckBox", required = false) boolean active,
            RedirectAttributes redirectAttributes,
            Model model) {

        if (result.hasErrors()) {
            model.addAttribute("vaccineId", vaccineRepository.findById(id).orElse(null).getVaccineId());

            return "vaccine/update-vaccine";
        }

        if (active)
            vaccine.setVaccineStatus(StatusEnum.ACTIVE);
        else
            vaccine.setVaccineStatus(StatusEnum.IN_ACTIVE);

        vaccineRepository.save(vaccine);

        return "redirect:/vaccine_list";
    }


    // IMPORT
    @GetMapping("/import_vaccine")
    public String ImportVaccine() {
        return "vaccine/import_vaccine";
    }
	

}
