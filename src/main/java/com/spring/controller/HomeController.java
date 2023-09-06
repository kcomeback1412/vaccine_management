package com.spring.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping(value = {"","/","/index","/home","/dashboard"})
    public String homePageUI(Model model){
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	
    	model.addAttribute("userName", authentication.getName());
    	
    	model.addAttribute("userRole", authentication.getAuthorities().toString());
    	
        return "dashboard";
    }

}
