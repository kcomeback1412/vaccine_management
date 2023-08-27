package com.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class DashboardController {

    @GetMapping("/dashboard")
    public String dashboardPage() {
        return "dashboard";
    }
    
    @GetMapping("/login")
    public String loginUI() {
    	return "login";
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
