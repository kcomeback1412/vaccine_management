package com.spring.controller;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.consts.RoleEnum;
import com.spring.entities.UserDetail;
import com.spring.entities.Users;
import com.spring.repositories.UserDetailRepository;
import com.spring.repositories.UsersRepository;
import com.spring.service.InjectionResultService;
import com.spring.service.UserDetailsService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/customer-manage")
public class CustomerController {
	
	@Autowired
    UserDetailsService userDetailsService;

	@Autowired
	UsersRepository usersRepository;

	@Autowired
	UserDetailRepository userDetailRepository;
	
	@Autowired
	PasswordEncoder encoder;
	
	@Autowired
	InjectionResultService injectionResultService;


	@GetMapping("/customer_list")
	public String CustomerList(@RequestParam(name = "pageNum", defaultValue = "1") Integer pageNum,
			@RequestParam(name = "pageSize", defaultValue = "5") Integer pageSize, Model model, HttpSession session) {

		Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
		
		Page<UserDetail> pageUserDetail = userDetailRepository.findAllCustomerByRole(pageable, RoleEnum.CUSTOMER);
		model.addAttribute("pageUserDetail", pageUserDetail);
		String id = "";
		Integer totalCustomer = userDetailsService.countAllCustomer();
		session.setAttribute("userDetailId", id);
		model.addAttribute("start", (pageNum - 1) * pageSize + 1);
		
		if (pageNum != pageUserDetail.getTotalPages()) {
            model.addAttribute("end", pageNum * pageSize);
        } else {
            model.addAttribute("end", totalCustomer);
        }
		model.addAttribute("currentPage", pageNum);
		
		model.addAttribute("totalCustomer", totalCustomer);
		model.addAttribute("pageSize", pageSize);
		
		return "customer/customer_list";
	}

	@ModelAttribute("pageUserDetail")
	Page<UserDetail> pageData() {
		Integer pageNum = 1;
		Integer pageSize = 5;
		Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
		Page<UserDetail> pageUserDetail = userDetailRepository.findAllCustomerByRole(pageable, RoleEnum.CUSTOMER);
		return pageUserDetail;
	}

	// Create
	@GetMapping("/create-customer")
	public String createCustomerUI(Model model) {
		return "customer/create-customer";
	}
	
	@PostMapping(value = "/create-customer")
	public String createCustomer(@ModelAttribute("customerInfo")Users customer,
			@ModelAttribute("userInfo") UserDetail account
			) {
		
		customer.setPassword(encoder.encode(customer.getPassword()));
		customer.setRoleEnum(RoleEnum.CUSTOMER);
		customer.setPassword(encoder.encode(customer.getPassword()));

		usersRepository.save(customer);
		account.setUsers2(customer);
		userDetailRepository.save(account);
		return "redirect:/customer-manage/customer_list";
	}

	// Delete
	@PostMapping(value = "/update-delete-customer", params = "delete")
	public String deleteCustomer(HttpServletRequest httpServletRequest) {
		if (httpServletRequest.getParameterValues("id") != null) {
			for (String id : httpServletRequest.getParameterValues("id")) {
				List<String> listId = new ArrayList<String>();
				listId.add(id);
				injectionResultService.deleteInjectionResultByListId(listId);
				userDetailRepository.deleteById(Integer.parseInt(id));
				usersRepository.deleteById(Integer.parseInt(id));
			}
			return "redirect:/customer-manage/customer_list";
		} else {
			return "redirect:/customer-manage/customer_list";
		}
	}

	// Update
	@PostMapping(value = "/update-delete-customer", params = "update")
	public String updateCustomerUI(HttpServletRequest httpServletRequest, HttpSession httpSession,
			@ModelAttribute("userDetailInfo") UserDetail userDetail, @ModelAttribute("userInfo") Users user,
			Model model) {

		if (httpServletRequest.getParameterValues("id") != null
				&& httpServletRequest.getParameterValues("id").length < 2) {
			for (String id : httpServletRequest.getParameterValues("id")) {
				userDetail = (UserDetail) userDetailRepository.findByIdUserDetail(Integer.parseInt(id));
				user = (Users) usersRepository.findByIdUser(id);
				model.addAttribute("userDetailInfo", userDetail);
				model.addAttribute("userInfo", user);
				System.out.println(httpServletRequest.getParameterValues("id").length);
			}
			return "customer/update-customer";
		} else {
			return "redirect:/customer-manage/customer_list";
		}
	}
	
	// Update by id
	@PostMapping(value = "/update-delete-customer", params = "save-update")
	public String updateCustomerInfo(@ModelAttribute("userDetailInfo") UserDetail userDetail,
			@ModelAttribute("userInfo") Users user, HttpServletRequest httpServletRequest,
		 	@RequestParam(name = "newPassword") String newPassword
	) {

		for (String id : httpServletRequest.getParameterValues("userId")) {
			Users userDB = (Users) usersRepository.findByIdUser(id);
			UserDetail userDetailDB = (UserDetail) userDetailRepository.findByIdUserDetail(Integer.parseInt(id));

			userDetailDB.setFullName(userDetail.getFullName());
			userDetailDB.setDateOfBirth(userDetail.getDateOfBirth());
			userDetailDB.setGender(userDetail.getGender());
			userDetailDB.setAddress(userDetail.getAddress());
			userDetailDB.setPhone(userDetail.getPhone());

			// update password if new password != null
			if(newPassword != null) {
				userDB.setPassword(encoder.encode(newPassword));
			}

			usersRepository.save(userDB);
			userDetailDB.setUsers2(userDB);
			userDetailRepository.save(userDetailDB);
		}
		return "redirect:/customer-manage/customer_list";
	}

	@GetMapping("/update-customer")
	public String updateCustomer(Model model) {
		model.addAttribute("userDetailInfo", new UserDetail());
		model.addAttribute("userInfo", new Users());
		return "customer/update-customer";
	}

	// Search
	@PostMapping(value = "/search-customer", params = "search")
	public String searchCustomer(HttpServletRequest httpServletRequest,
			@RequestParam(name = "pageNum", defaultValue = "1") Integer pageNum,
			@RequestParam(name = "pageSize", defaultValue = "5") Integer pageSize, Model model, HttpSession session) {
		String id = "";
		for (String idAndName : httpServletRequest.getParameterValues("search")) {
			if (!(idAndName.equals(id))) {
				Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
				model.addAttribute("currentPage", pageNum);
				Page<UserDetail> pageUserDetail = userDetailRepository.findUserDetailCustomerWithPagin(idAndName,RoleEnum.CUSTOMER, pageable);
				model.addAttribute("pageUserDetail", pageUserDetail);
				session.setAttribute("userDetailId", idAndName);
				model.addAttribute("start", (pageNum - 1) * pageSize + 1);
				Integer countCustomer = userDetailRepository.countAllCustomerByRole(RoleEnum.CUSTOMER,idAndName);
				Integer totalCustomer = userDetailsService.countAllCustomer();
				model.addAttribute("end", countCustomer);
				model.addAttribute("totalCustomer", totalCustomer);
				
				model.addAttribute("pageSize", pageSize);
				return "customer/customer_list";
			} else {
				return "redirect:/customer-manage/customer_list";
			}
		}
		return "redirect:/customer-manage/customer_list";
	}

	// Show list
	@PostMapping(value = "/list-customer", params = "show")
	public String showList(@RequestParam(name = "pageNum", defaultValue = "1") Integer pageNum, Model model,
			HttpSession session, HttpServletRequest pageSize) {
		String blank = "";
		
		for (String size : pageSize.getParameterValues("show")) {
			if (!(size.equals(blank))) {
				Pageable pageable = PageRequest.of(pageNum - 1, Integer.parseInt(size));
				model.addAttribute("currentPage", pageNum);
				Page<UserDetail> pageUserDetail = userDetailRepository.findAllCustomerByRole(pageable, RoleEnum.CUSTOMER);
				model.addAttribute("pageUserDetail", pageUserDetail);
				String id = "";
				session.setAttribute("userDetailId", id);
				
				return "customer/customer_list";
			} else {
				return "redirect:/customer-manage/customer_list";
			}
		}
		return "customer/customer_list";
	}
	
	@GetMapping(value = "/search-customer")
	public String searchCustomer() {
		return "redirect:/customer-manage/customer_list";
	}
	
	@GetMapping(value = "/list-customer")
	public String listCustomer() {
		return "redirect:/customer-manage/customer_list";
	}
	
}
