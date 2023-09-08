package com.spring.service.impl;

import com.spring.entities.VaccineType;
import com.spring.repositories.VaccineTypeRepository;
import com.spring.service.VaccineTypeService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class VaccineTypeServiceImpl implements VaccineTypeService {
    @Autowired
    private VaccineTypeRepository vaccineTypeRepository;
    
	@Override
	public VaccineType save(VaccineType vaccineType) {
		return vaccineTypeRepository.save(vaccineType);
	}
	
	@Override
	public Optional<VaccineType> findById(String vaccineTypeId) {
		return vaccineTypeRepository.findById(vaccineTypeId);
	}
	
	@Override
	public VaccineType getById(String vaccineTypeId) {
		return vaccineTypeRepository.getById(vaccineTypeId);
	}

	@Override
	public VaccineType checkVaccineTypeId(String vaccineTypeId) {
		return vaccineTypeRepository.checkVaccineTypeId(vaccineTypeId);
	}

	@Override
	public List<VaccineType> findAllByNameLike(String vaccineTypeName) {
		return vaccineTypeRepository.findAllByNameLike(vaccineTypeName);
	}

	@Override
	public Page<VaccineType> findPageByNameLike(String vaccineTypeName, Pageable pageable) {
		return vaccineTypeRepository.findPageByNameLike(vaccineTypeName, pageable);
	}

}
