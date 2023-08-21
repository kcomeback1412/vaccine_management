package com.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class DashboardController {

    @GetMapping("/dashboard")
    public String dashboardPage() {
        return "dashboard"; // Đây là tên của file HTML template của trang dashboard, ví dụ: dashboard.html
    }
    
    @GetMapping("/employeeList")
    public String employeeList() {
    	return "employee/employeeList";
    }
    
}
