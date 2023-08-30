package com.spring.controller;

import com.spring.consts.RoleEnum;
import com.spring.entities.*;
import com.spring.service.UserDetailsService;
import com.spring.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
//@RequestMapping("/employee-management/")
public class EmployeeController {
    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    UsersService usersService;

    @GetMapping("/create-employee")
    String addEmployeeUI() {
        return "employee/create-employee";
    }

//    @PostMapping("/create-employee")
//    public String createEmployee(
//        @ModelAttribute("employeeToCreate") UserDetail userDetail,
//        @RequestParam(name = "username") String username,
//        @RequestParam(name = "password") String password
//        ) {
//        Users user = new Users();
//        user.setUserName(username);
//        user.setPassword(password);
//        usersService.save(user);
//
//        Roles role = new Roles();
//        role.setId(RoleEnum.STAFF.getId());
//
//        UserRoles userRoles = new UserRoles();
//        UserRolesId userRolesId = new UserRolesId();
//        userRolesId.setUserId(user.getUsersId());
//        userRolesId.setRoleId(RoleEnum.STAFF.getId());
//
//        userRoles.setId(userRolesId);
//        userRoles.setUsers1(user);
//        userRoles.setRoles(role);
//        userRolesService.save(userRoles);
//
//        userDetail.setUsers2(user);
//        userDetailsService.save(userDetail);
//        return "redirect:/create-employee";
//    }

    @GetMapping("/update-employee")
    String updateEmployeeUI() {
        return "employee/update-employee";
    }
    
    @GetMapping("/employee-list")
    public String employeeListUI(
        @RequestParam(name = "pageNum", defaultValue = "1") Integer pageNum,
        @RequestParam(name = "pageSize", defaultValue = "5") Integer pageSize,
        Model model
    ) {
        Pageable pageable = PageRequest.of(pageNum - 1,pageSize);
        Page<UserDetail> userDetails = userDetailsService.findAll(pageable);

        if(pageNum != null) {
            model.addAttribute("currentPage", pageNum);
        } else {
            model.addAttribute("currentPage", 0);
        }

        if(userDetails != null) {
            model.addAttribute("userDetails", userDetails);
        }
    	return "employee/employee-list";
    }
}
