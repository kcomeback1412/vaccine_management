package com.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/reports-management")
public class ReportsController {

    @GetMapping("/report_injection_result")
    public String reportInjectionNewsUI() {
        return "reports/report_injection_result";
    }

    @GetMapping("/report_injection_chart")
    public String reportInjectionChartUI() {
        return "reports/report_injection_chart";
    }
}
