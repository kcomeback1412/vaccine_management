package com.spring.service.impl;

import com.spring.repositories.InjectionScheduleRepository;
import com.spring.service.InjectionScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InjectionScheduleServiceImpl implements InjectionScheduleService {
    @Autowired
    private InjectionScheduleRepository injectionScheduleRepository;
}
