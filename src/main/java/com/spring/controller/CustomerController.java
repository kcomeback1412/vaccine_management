package com.spring.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
    public String CustomerList(
    		Model model
    		) {
    	List<UserDetail> customer = userDetailRepository.findAll();    	
    	model.addAttribute("customerList", customer);
        return "customer/customer_list";
    }
    
    @PostMapping("/create-customer")
    public String createCustomer(
    		@ModelAttribute("customerInfo") Users customer,
    		@ModelAttribute("userInfo") UserDetail account
    		) {
    	String id = UUID.nameUUIDFromBytes(customer.getUserName().getBytes()).toString();
    	customer.setUsersId(id);
    	usersRepository.save(customer);
    	account.setUsers2(customer);
    	userDetailRepository.save(account);
    	System.out.println(customer.toString()+ "+" + account.toString());
    	return "redirect:/customer_list";
    }
    
    @GetMapping("/delete-customer/{id}")
    public String deleteCustomer(@PathVariable("id") String id ){
    	
    	userDetailRepository.deleteById(Integer.parseInt(id));
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
