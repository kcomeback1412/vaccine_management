package com.spring.service.impl;

import com.spring.entities.Prevention;
import com.spring.repositories.PreventionRepository;
import com.spring.service.PreventionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PreventionServiceImpl implements PreventionService {
    @Autowired
    PreventionRepository preventionRepository;

    @Override
    public List<Prevention> findAllPrevention() {
        return preventionRepository.findAll();
    }
}
