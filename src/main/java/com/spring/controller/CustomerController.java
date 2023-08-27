package com.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CustomerController {

    @GetMapping("/customer_list")
    public String CustomerList() {
        return "customer/customer_list";
    }

    @GetMapping("/update-customer")
    public String updateCustomerUI() {
        return "customer/update-customer";
    }

    @GetMapping("/create-customer")
    public String createCustomerUI() {
        return "customer/create-customer";
    }
}
