package com.spring.controller;

import com.spring.repositories.InjectionResultRepository;
import com.spring.repositories.InjectionScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InjectionScheduleController {
	
	@Autowired
	private InjectionScheduleRepository injectionScheduleRepository;
	
	@GetMapping("/addInjectionSchedule")
	public String addInjectionScheduleUI() {
		return "Schedule/addInjectionSchedule";
    }
}
