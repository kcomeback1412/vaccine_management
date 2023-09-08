package com.spring.service;

import com.spring.entities.VaccineType;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface VaccineTypeService {
	
	VaccineType save(VaccineType vaccineType);
	
	Optional<VaccineType> findById(String vaccineTypeId);
	
	VaccineType getById(String vaccineTypeId);
	
	VaccineType checkVaccineTypeId(String vaccineTypeId);
	
	List<VaccineType> findAllByNameLike(String vaccineTypeName);
	
	Page<VaccineType> findPageByNameLike(String vaccineTypeName, Pageable pageable);
}
