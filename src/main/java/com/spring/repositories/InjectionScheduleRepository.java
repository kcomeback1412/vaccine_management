package com.spring.repositories;

import com.spring.entities.InjectionSchedule;
import com.spring.entities.Vaccine;
import com.spring.entities.VaccineType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface InjectionScheduleRepository extends JpaRepository<InjectionSchedule,String> {
    @Query("SELECT is FROM InjectionResult is WHERE is.injectionResultId = ?1")
    public InjectionSchedule checkInjectionScheduleId(String injectionScheduleId);

    @Query("SELECT v FROM InjectionSchedule v WHERE v.vaccine1.vaccineName like %?1%")
    public Page<InjectionSchedule> findPageByNameLike(String vaccineName, Pageable pageable);


}
