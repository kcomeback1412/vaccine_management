package com.spring.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Entity
public class VaccineType implements Serializable {
    @Id
    @Column(name = "VACCINE_TYPE_ID", length = 36)
    private String vaccineTypeId;

    @Column(name = "DESCRIPTION", length = 200)
    private String description;

    @Column(name = "VACCINE_TYPE_Name", length = 200)
    private String vaccineTypeName;

    @OneToMany(mappedBy = "vaccineType")
    private List<Vaccine> vaccineList;

}
