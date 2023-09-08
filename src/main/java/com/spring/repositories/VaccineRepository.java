package com.spring.repositories;

import com.spring.entities.Vaccine;
import com.spring.entities.VaccineType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface VaccineRepository extends JpaRepository<Vaccine,String> {
    @Query("SELECT d FROM Vaccine d WHERE d.vaccineId = ?1")
    public Vaccine checkVaccineId(String vaccineId);
}
