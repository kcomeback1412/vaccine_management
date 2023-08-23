package com.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EmployeeController {

    @GetMapping("/add-employee")
    String addEmployeeUI() {
        return "employee/add-employee.html";
    }
}
