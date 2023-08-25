package com.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CustomerController {

    @GetMapping("/update-customer")
    public String updateCustomerUI() {
        return "customer/update-customer";
    }

    @GetMapping("/create-customer")
    public String createCustomerUI() {
        return "customer/create-customer";
    }
}
