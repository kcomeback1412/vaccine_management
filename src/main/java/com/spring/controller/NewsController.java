package com.spring.controller;

import com.spring.entities.News;
import com.spring.entities.NewsType;
import com.spring.entities.VaccineType;
import com.spring.repositories.NewsRepository;
import com.spring.repositories.NewsTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.*;

@Controller
@RequestMapping("/news-management")
public class NewsController {
    @Autowired
    private NewsRepository newsRepository;
    @Autowired
    private NewsTypeRepository newsTypeRepository;

    @GetMapping("/create_news")
    public String createNewsUI(Model model) {
        model.addAttribute("news", new News());
        return "news/create_news";
    }

        @GetMapping("/news_list")
        public String getNewsList(
                @RequestParam(defaultValue = "") String search,
                @RequestParam(name = "pageNum", required = false,defaultValue = "1") Integer pageNum,
                @RequestParam(name = "pageSize", required = false,defaultValue = "5") Integer pageSize,
                Model model) {

            model.addAttribute("option", pageSize);
            Sort sort = Sort.by("newsId").ascending();

            News news = new News();
            model.addAttribute("news", news);
            if (pageNum < 1) {
                pageNum = 1;
            }
            Pageable pageable = PageRequest.of(pageNum - 1, pageSize, sort);
            Page<News> newsPage;
            if (!search.isEmpty())
                newsPage = newsRepository.searchByTitle(search, pageable);
            else {
                newsPage = newsRepository.findAll(pageable);
            }
            model.addAttribute("start", (pageNum - 1) * pageSize + 1);

            if (pageNum != newsPage.getTotalPages()) {
                model.addAttribute("end", pageNum * pageSize);
            } else {
                model.addAttribute("end", newsPage.getTotalElements());
            }

            model.addAttribute("total", newsPage.getTotalElements());


            model.addAttribute("search", search);
            model.addAttribute("newsPage", newsPage);

            return "news/news_list";
        }

    @PostMapping("/create_news")
    public String createNewsList(
            @ModelAttribute("news") @Validated News news,
            Model model,
            RedirectAttributes redirectAttributes) {
        news.setPostDate(new Date());
        news.setNewsId(UUID.randomUUID().toString());
        newsRepository.save(news);
        redirectAttributes.addFlashAttribute("msg23", "Create successfully!");
        return "redirect:/news-management/news_list";
    }

    @GetMapping("/update_news/{id}")
    public String updateNewsUI(
            @PathVariable String id,
            Model model) {
        Optional<News> optionalNews = newsRepository.findById(id);
        if (optionalNews.isPresent()) {
            News news = optionalNews.get();
            model.addAttribute("news", news);

            return "news/update_news";
        } else {
            return "redirect:/news-management/news_list";
        }
    }

    @PostMapping("/update_news")
    public String updateNews(
            @RequestParam(name = "newsId", required = false) String id,
            Model model,
            @ModelAttribute News updatedNews,
            RedirectAttributes redirectAttributes) {
        Optional<News> optionalNews = newsRepository.findById(id);
        if (optionalNews.isPresent()) {
            News news = optionalNews.get();
            news.setTitle(updatedNews.getTitle());
            news.setContent(updatedNews.getContent());
            news.setPreview(updatedNews.getPreview());
            news.setPostDate(new Date());

            newsRepository.save(news);
            redirectAttributes.addFlashAttribute("msg22", "Update successfully!");
            return "redirect:/news-management/news_list";
        } else {
            return "redirect:/news-management/news_list";
        }
    }


    @PostMapping("/delete_news")
    public String deleteNews(@RequestParam(name = "newsIds", required = false) String[] newsIds) {
        if (newsIds != null) {
            for (String id : newsIds) {

                newsRepository.deleteById(id);
            }
        }
        return "redirect:/news-management/news_list";
    }
}

