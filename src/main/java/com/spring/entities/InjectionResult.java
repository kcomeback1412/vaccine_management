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

	private static final long serialVersionUID = 1L;

	@Id
    @Column(name = "INJECTION_RESULT_ID", length = 36)
    private String injectionResultId;

    @Column(name = "INJECTION_DATE")
    private LocalDate injectionDate;

    @Column(name = "NEXT_INJECTION_DATE")
    private LocalDate nextInjectionDate;

    @Column(name = "NUMBER_OF_INJECTION", length = 100)
    private String numberOfInjection;

    @ManyToOne
    @JoinColumn(name = "VACCINE_ID")
    private Vaccine vaccine;

    @OneToOne
    @JoinColumn(name = "INJECTION_SCHEDULE_ID")
    InjectionSchedule injectionSchedule;

    @ManyToOne
    @JoinColumn(name = "USERS_ID")
    private Users users3;

    @ManyToOne
    @JoinColumn(name = "PLACE_ID")
    private Place place;

    @ManyToOne
    @JoinColumn(name = "PREVENTION_ID")
    private Prevention prevention;

}
