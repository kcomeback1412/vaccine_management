package com.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class ReportsController {
    @GetMapping("/report_injection_result")
    public String reportInjectionNewsUI() {
        return "reports/report_injection_result";
    }
}
