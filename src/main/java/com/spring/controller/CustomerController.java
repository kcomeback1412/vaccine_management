package com.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.spring.entities.UserDetail;
import com.spring.entities.Users;
import com.spring.repositories.UserDetailRepository;
import com.spring.repositories.UsersRepository;

@Controller
public class CustomerController {
	
	@Autowired
	UsersRepository usersRepository;
	
	@Autowired
	UserDetailRepository userDetailRepository;

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
    
    @PostMapping("/create-customer")
    public String createCustomer(
    		@ModelAttribute("customerInfo") Users customer,
    		@ModelAttribute("userInfo") UserDetail account
    		) {
    	customer.setUsersId("1213");
    	usersRepository.save(customer);
    	account.setUsers2(customer);
    	userDetailRepository.save(account);
    	System.out.println(customer.toString()+ "+" + account.toString());
    	return "redirect:/customer_list";
    }
    
}
