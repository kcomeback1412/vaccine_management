package com.spring.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@Entity
public class InjectionResult implements Serializable {
    @Id
    @Column(name = "INJECTION_RESULT_ID", length = 36)
    private String injectionResultId;

    @Column(name = "INJECTION_DATE")
    private LocalDate injectionDate;

    @Column(name = "INJECTION_PLACE", length = 255)
    private String injectionPlace;

    @Column(name = "NEXT_INJECTION_DATE")
    private LocalDate nextInjectionDate;

    @Column(name = "NUMBER_OF_INJECTION", length = 100)
    private String numberOfInjection;

    @Column(name = "PREVENTION", length = 100)
    private String prevention;

    @ManyToOne
    @JoinColumn(name = "VACCINE_ID")
    private Vaccine vaccine2;

    @ManyToOne
    @JoinColumn(name = "USERS_ID")
    private Users users3;
}
