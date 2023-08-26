package com.spring.repositories;

import com.spring.entities.VaccineType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface VaccineTypeRepository extends JpaRepository<VaccineType,String> {
	
	@Query("SELECT vt FROM VaccineType vt WHERE vt.vaccineTypeId = ?1")
	public VaccineType checkVaccineTypeId(String vaccineTypeId);
}
