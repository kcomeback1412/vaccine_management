package com.spring.repositories;

import com.spring.entities.Vaccine;
import com.spring.entities.VaccineType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VaccineRepository extends JpaRepository<Vaccine,String> {
    @Query("SELECT d FROM Vaccine d WHERE d.vaccineId = ?1")
    public Vaccine checkVaccineId(String vaccineId);


    @Query("SELECT v FROM Vaccine v WHERE v.vaccineName like %?1%")
    public Page<Vaccine> findPageByNameLike(String vaccineName, Pageable pageable);

}
