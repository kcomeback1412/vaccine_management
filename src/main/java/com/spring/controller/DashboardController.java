package com.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class DashboardController {

    @GetMapping("/dashboard")
    public String dashboardPage() {
        return "dashboard";
    }
    
    @GetMapping("/employee_list")
    public String employeeList() {
    	return "employee/employee_list";
    }
    
    @GetMapping("/login")
    public String loginUI() {
    	return "login";
    }
    
    @GetMapping("/addVaccineType")
    public String addVaccineTypeUI() {
    	return "addVaccineType";
    }
    
}
