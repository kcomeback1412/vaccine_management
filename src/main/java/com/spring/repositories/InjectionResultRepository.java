package com.spring.repositories;

import com.spring.entities.InjectionResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InjectionResultRepository extends JpaRepository<InjectionResult, String> {
}
