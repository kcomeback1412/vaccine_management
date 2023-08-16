package com.spring.repositories;

import com.spring.entities.Customer;
import com.spring.entities.InjectionSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InjectionScheduleRepository extends JpaRepository<InjectionSchedule,String> {
}
