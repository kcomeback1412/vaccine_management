package com.spring.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@Entity
public class Vaccine implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "VACCINE_ID", length = 36)
    private String vaccineId;

    @Column(name = "CONTRAINDICATION", length = 200)
    private String contraindication;

    @Column(name = "INDICATION", length = 200)
    private String indication;

    @Column(name = "NUMBER_OF_INJECTION")
    private Integer numberOfInjection;

    @Column(name = "ORIGIN", length = 50)
    private String origin;

    @Column(name = "TIME_BEGIN_NEXT_INJECTION")
    private LocalDate timeBeginNextInjection;

    @Column(name = "TIME_END_NEXT_INJECTION")
    private LocalDate timeEndNextInjection;

    @Column(name = "USAGE", length = 200)
    private String usage;

    @Column(name = "VACCINE_NAME", length = 100)
    private String vaccineName;

}
