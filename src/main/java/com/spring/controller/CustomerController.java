package com.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.entities.UserDetail;
import com.spring.entities.Users;
import com.spring.repositories.UserDetailRepository;
import com.spring.repositories.UsersRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class CustomerController {

	@Autowired
	UsersRepository usersRepository;

	@Autowired
	UserDetailRepository userDetailRepository;

	@GetMapping("/customer_list")
	public String CustomerList(Model model) {
		List<UserDetail> customer = userDetailRepository.findAll();
		model.addAttribute("customerList", customer);
		return "customer/customer_list";
	}

	// Create
	@RequestMapping(value = "/update-delete", params = "create", method = RequestMethod.POST)
	public String customerCreatePage() {
		return "customer/create-customer";
	}

	@GetMapping("/create-customer")
	public String createCustomerUI() {
		return "customer/create-customer";
	}

	@PostMapping("/create-customer")
	public String createCustomer(@ModelAttribute("customerInfo") Users customer,
			@ModelAttribute("userInfo") UserDetail account) {
		usersRepository.save(customer);
		account.setUsers2(customer);
		userDetailRepository.save(account);
		return "redirect:/customer_list";
	}

	// Delete
	@RequestMapping(value = "/update-delete", params = "delete", method = RequestMethod.POST)
	public String findCustomer(HttpServletRequest httpServletRequest) {
		try {
			if (httpServletRequest.getParameterValues("id") != null) {
				for (String id : httpServletRequest.getParameterValues("id")) {
					userDetailRepository.deleteById(Integer.parseInt(id));
				}
			}
			return "redirect:/customer_list";
		} catch (Exception e) {
			return "customer/customer_list";
		}
	}

	// Update
	@RequestMapping(value = "/update-delete", params = "update", method = RequestMethod.POST)
	public String updateCustomerUI(HttpServletRequest httpServletRequest,
			HttpSession httpSession,
			@ModelAttribute("userInfo") UserDetail userDetail,
			Model model
			) {
		try {
			if (httpServletRequest.getParameterValues("id") != null) {
				for (String id : httpServletRequest.getParameterValues("id")) {
					userDetail = (UserDetail) userDetailRepository.findByIdUserDetail(id);
					model.addAttribute("userInfo", userDetail);
				}
			}
			return "customer/update-customer";
		} catch (Exception e) {
			return "customer/customer_list";
		}
	}
	
//	@PostMapping("/update-customer")
//	public String updateCustomerInfo() {
//		
//		return "customer/update-customer";
//	}

	@GetMapping("/update-customer")
	public String updateCustomer() {
		
		return "customer/update-customer";
	}

}
