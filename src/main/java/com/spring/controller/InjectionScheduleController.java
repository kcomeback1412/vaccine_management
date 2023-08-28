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

	@GetMapping("/schedule-list")
	public String scheduleList() {
		return "schedule/schedule-list";
	}
	
	@GetMapping("/create_injectionSchedule")
	public String CreateInjectionScheduleUI() {
		return "Schedule/create_injectionSchedule";
    }

	@GetMapping("/update_injectionSchedule")
	public String updateInjectionScheduleUI() {
		return "schedule/update_injectionSchedule";
	}


}
