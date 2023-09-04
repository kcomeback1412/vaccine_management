package com.spring.controller;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.consts.RoleEnum;
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
	public String CustomerList(@RequestParam(name = "pageNum", defaultValue = "1") Integer pageNum,
			@RequestParam(name = "pageSize", defaultValue = "3") Integer pageSize, Model model,
			HttpSession session
			) {

		Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
		 model.addAttribute("currentPage", pageNum);

		Page<UserDetail> pageUserDetail = userDetailRepository.findAll(pageable);
		model.addAttribute("pageUserDetail", pageUserDetail);
		String id = "";
		session.setAttribute("userDetailId",id);
		return "customer/customer_list";
	}
	
	@ModelAttribute("pageUserDetail")
	Page<UserDetail> pageData(){
		Integer pageNum = 1;
		Integer pageSize = 5;
		Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
		Page<UserDetail> pageUserDetail = userDetailRepository.findAll(pageable);
		return pageUserDetail;
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
		customer.setRoleEnum(RoleEnum.ROLE_CUSTOMER);
		usersRepository.save(customer);
		account.setUsers2(customer);
		userDetailRepository.save(account);
		return "redirect:/customer_list";
	}

	// Delete
	@RequestMapping(value = "/update-delete", params = "delete", method = RequestMethod.POST)
	public String deleteCustomer(HttpServletRequest httpServletRequest) {
		
			if (httpServletRequest.getParameterValues("id") != null) {
				for (String id : httpServletRequest.getParameterValues("id")) {
					userDetailRepository.deleteById(Integer.parseInt(id));
					usersRepository.deleteById(Integer.parseInt(id));
				}
				return "redirect:/customer_list";
			}else {
				return "redirect:/customer_list";
			}
	}

	// Update
	@RequestMapping(value = "/update-delete", params = "update", method = RequestMethod.POST)
	public String updateCustomerUI(HttpServletRequest httpServletRequest,
			HttpSession httpSession,
			@ModelAttribute("userDetailInfo") UserDetail userDetail,
			@ModelAttribute("userInfo") Users user,
			Model model
			) {
		
			if (httpServletRequest.getParameterValues("id") != null && httpServletRequest.getParameterValues("id").length < 2) {
					for (String id : httpServletRequest.getParameterValues("id")) {
						userDetail = (UserDetail) userDetailRepository.findByIdUserDetail(id);
						user = (Users) usersRepository.findByIdUser(id);
						model.addAttribute("userDetailInfo", userDetail);
						model.addAttribute("userInfo", user);
						System.out.println(httpServletRequest.getParameterValues("id").length);
					}
					return "customer/update-customer";
			}else {
				return "redirect:/customer_list";
			}
	}
	
	@RequestMapping(value = "/update-delete", params = "save", method = RequestMethod.POST)
	public String updateCustomerInfo(
			@ModelAttribute("userDetailInfo") UserDetail userDetail,
			@ModelAttribute("userInfo") Users user,
			HttpServletRequest httpServletRequest
			) {
		
		for (String id : httpServletRequest.getParameterValues("userId")) {
			Users userDB = (Users) usersRepository.findByIdUser(id);
			UserDetail userDetailDB = (UserDetail) userDetailRepository.findByIdUserDetail(id);
			System.out.println(user.getUserName());
			
			
			userDetailDB.setFullName(userDetail.getFullName());
			userDetailDB.setDateOfBirth(userDetail.getDateOfBirth());
			userDetailDB.setGender(userDetail.getGender());
			userDetailDB.setAddress(userDetail.getAddress());
			userDetailDB.setPhone(userDetail.getPhone());
			
			usersRepository.save(userDB);
			userDetailDB.setUsers2(userDB);
			userDetailRepository.save(userDetailDB);
		}
		return "redirect:/customer_list";
	}
	
	@GetMapping("/update-customer")
	public String updateCustomer(
			Model model
			) {
		model.addAttribute("userDetailInfo", new UserDetail());
		model.addAttribute("userInfo", new Users());
		return "customer/update-customer";
	}
	
	//Search
	@RequestMapping(value = "/update-delete", params = "search", method = RequestMethod.POST)
	public String searchCustomer(
			HttpServletRequest httpServletRequest,
			@RequestParam(name = "pageNum", defaultValue = "1") Integer pageNum,
			@RequestParam(name = "pageSize", defaultValue = "3") Integer pageSize, 
			Model model,
			HttpSession session
			) {
		
		if(httpServletRequest.getParameterValues("search") != null) {
			for (String idAndName : httpServletRequest.getParameterValues("search")) {
				Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
				 model.addAttribute("currentPage", pageNum);

				Page<UserDetail> pageUserDetail = userDetailRepository.findUserDetailWithPagin(idAndName, pageable);
				model.addAttribute("pageUserDetail", pageUserDetail);
				session.setAttribute("userDetailId",idAndName);
			}
			return "customer/customer_list";
		}else {
			return "redirect:/customer_list";
		}
	}
	

}
