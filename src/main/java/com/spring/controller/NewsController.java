package com.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NewsController {
    @GetMapping("/create_news")
    public String createNewsUI() {
        return "news/create_news";
    }

    @GetMapping("/news_list")
    public String newListUI() {
        return "news/news_list";
    }

    @GetMapping("/update_news")
    public String updateNewsUI() {
        return "news/update_news";
    }
}
