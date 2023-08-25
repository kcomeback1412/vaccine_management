package com.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class DashboardController {

    @GetMapping("/dashboard")
    public String dashboardPage() {
        return "dashboard";
    }
    
    @GetMapping("/login")
    public String loginUI() {
    	return "login";
    }
    
    @GetMapping("/create_news")
    public String createNewsUI() {
        return "news/create_news";
    }

    @GetMapping("/news_list")
    public String newListUI() {
        return "news/news_list";
    }
    
}
