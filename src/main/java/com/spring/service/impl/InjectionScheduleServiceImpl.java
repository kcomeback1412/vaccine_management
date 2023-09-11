package com.spring.service.impl;

import com.spring.consts.RoleEnum;
import com.spring.entities.InjectionSchedule;
import com.spring.entities.UserDetail;
import com.spring.entities.Vaccine;
import com.spring.repositories.InjectionScheduleRepository;
import com.spring.repositories.VaccineRepository;
import com.spring.service.InjectionScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InjectionScheduleServiceImpl implements InjectionScheduleService {
    @Autowired
    private InjectionScheduleRepository injectionScheduleRepository;

    @Autowired
    private VaccineRepository vaccineRepository;


    @Override
    public Page<InjectionSchedule> findPageByNameLike(String vaccineName, Pageable pageable) {
        return injectionScheduleRepository.findPageByNameLike(vaccineName, pageable);
    }
}
