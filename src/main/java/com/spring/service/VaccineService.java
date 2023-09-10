package com.spring.service;

import com.spring.entities.Vaccine;

import java.util.List;

public interface VaccineService {
    List<Vaccine> findAllVaccine();
}
