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

    @GetMapping("/vaccine_list")
    public String VaccineList() {
        return "Vaccine/vaccine_list";
    }

    @GetMapping("/import_vaccine")
    public String ImportVaccine() {
        return "Vaccine/import_vaccine";
    }

    @GetMapping("/customer_list")
    public String CustomerList() {
        return "customer/customer_list";
    }

    @GetMapping("/vaccine_result_list")
    public String VaccineResultList() {
        return "Vaccine/vaccine_result_list";
    }
}
