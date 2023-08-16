package com.spring.service.impl;

import com.spring.repositories.NewsRepository;
import com.spring.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NewsServiceImpl implements NewsService {
    @Autowired
    private NewsRepository newsRepository;
}
