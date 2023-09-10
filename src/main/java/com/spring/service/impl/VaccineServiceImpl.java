package com.spring.service.impl;

import com.spring.entities.Vaccine;
import com.spring.repositories.VaccineRepository;
import com.spring.service.VaccineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VaccineServiceImpl implements VaccineService {
    @Autowired
    private VaccineRepository vaccineRepository;

    @Override
    public List<Vaccine> findAllVaccine() {
        return vaccineRepository.findAll();
    }
}
