package com.spring.service.impl;

import com.spring.repositories.VaccineRepository;
import com.spring.service.VaccineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VaccineServiceImpl implements VaccineService {
    @Autowired
    private VaccineRepository vaccineRepository;
}
