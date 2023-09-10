package com.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.spring.entities.Users;

import jakarta.servlet.http.HttpSession;

@Controller
public class AuthController {
	
	@GetMapping("/login")
    public String loginUI(Model model){				
        model.addAttribute("userInfo", new Users());
        return "login";
    }
	
	@GetMapping("/logout")
    public String logout(HttpSession httpSession){
        httpSession.invalidate();
        return "redirect:/login";
    }
	
}
