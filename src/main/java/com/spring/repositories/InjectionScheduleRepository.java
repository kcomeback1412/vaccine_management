package com.spring.repositories;

import com.spring.entities.InjectionSchedule;
import com.spring.entities.Vaccine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface InjectionScheduleRepository extends JpaRepository<InjectionSchedule,String> {
    @Query("SELECT is FROM InjectionResult is WHERE is.injectionResultId = ?1")
    public InjectionSchedule checkInjectionScheduleId(String injectionScheduleId);
}
