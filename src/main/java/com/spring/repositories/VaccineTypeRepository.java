package com.spring.repositories;

import com.spring.entities.VaccineType;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface VaccineTypeRepository extends JpaRepository<VaccineType,String> {
	
	@Query("SELECT vt FROM VaccineType vt WHERE vt.vaccineTypeId = ?1")
	public VaccineType checkVaccineTypeId(String vaccineTypeId);
	
	@Query("SELECT vt FROM VaccineType vt WHERE vt.vaccineTypeName like %?1%")
	public List<VaccineType> findAllByNameLike(String vaccineTypeName);
	
	@Query("SELECT vt FROM VaccineType vt WHERE vt.vaccineTypeName like %?1%")
	public Page<VaccineType> findPageByNameLike(String vaccineTypeName, Pageable pageable);
}
