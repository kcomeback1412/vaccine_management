package com.spring.service.impl;

import com.spring.repositories.VaccineTypeRepository;
import com.spring.service.VaccineTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VaccineTypeServiceImpl implements VaccineTypeService {
    @Autowired
    private VaccineTypeRepository vaccineTypeRepository;
}
