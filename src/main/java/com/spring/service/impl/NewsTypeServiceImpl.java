package com.spring.service.impl;

import com.spring.repositories.NewsTypeRepository;
import com.spring.service.NewsTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NewsTypeServiceImpl  implements NewsTypeService {
    @Autowired
    private NewsTypeRepository newsTypeRepository;
}
