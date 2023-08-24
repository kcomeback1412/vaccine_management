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
    
    @GetMapping("/schedule_list")
    public String scheduleList() {
    	return "schedule/schedule_list";
    }
    
    @GetMapping("/vaccine_type_list")
    public String vaccineTypeList() {
    	return "vaccine/vaccine_type_list";
    }
    
    @GetMapping("/add_vaccine")
    public String addVaccine() {
    	return "vaccine/add_vaccine";
    }
    
    @GetMapping("/create_customer")
    public String createCustomer() {
    	return "customer/create_customer";
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
