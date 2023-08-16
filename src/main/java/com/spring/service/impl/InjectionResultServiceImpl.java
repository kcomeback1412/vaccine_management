package com.spring.service.impl;

import com.spring.repositories.InjectionResultRepository;
import com.spring.service.InjectionResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InjectionResultServiceImpl implements InjectionResultService {
    @Autowired
    private InjectionResultRepository injectionResultRepository;
}
