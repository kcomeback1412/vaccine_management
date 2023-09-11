package com.spring.repositories;


import com.spring.entities.Place;
import com.spring.entities.Prevention;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PreventionRepository extends JpaRepository<Prevention, Integer> {

	
}
