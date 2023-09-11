package com.spring.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.spring.consts.StatusEnum;

@Getter
@Setter
@Entity
public class VaccineType implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    @Column(name = "VACCINE_TYPE_ID", length = 50)
	@NotBlank(message = "{msg4}")
    private String vaccineTypeId;

    @Column(name = "VACCINE_TYPE_NAME", length = 50)
    @NotBlank(message = "{msg4}")
    private String vaccineTypeName;
    
    @Column(name = "VACCINE_TYPE_STATUS")
    @Enumerated(EnumType.STRING)
    private StatusEnum vaccineTypeStatus;
    
    @Column(name = "DESCRIPTION", length = 200)
    private String description;

    @OneToMany(mappedBy = "vaccineType")
    private List<Vaccine> vaccineList;

}
