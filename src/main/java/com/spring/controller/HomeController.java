package com.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping(value = {"","/","/index","/home","/dashboard"})
    public String homePageUI(){    	
        return "dashboard";
    }

}
