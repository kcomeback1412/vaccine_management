package com.spring.service;

import com.spring.entities.InjectionSchedule;
import com.spring.entities.UserDetail;
import com.spring.entities.Vaccine;
import com.spring.entities.VaccineType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface InjectionScheduleService {
    Page<InjectionSchedule> findPageByNameLike(String vaccineName, Pageable pageable);
}
