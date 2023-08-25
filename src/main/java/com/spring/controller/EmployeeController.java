package com.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EmployeeController {

    @GetMapping("/create-employee")
    String addEmployeeUI() {
        return "employee/create-employee";
    }

    @GetMapping("/update-employee")
    String updateEmployeeUI() {
        return "employee/update-employee";
    }
    
    @GetMapping("/employee-list")
    public String employeeList() {
    	return "employee/employee-list";
    }
}
