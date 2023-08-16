package com.spring.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@Entity
public class InjectionSchedule implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "INJECTION_SCHEDULE_ID", length = 36)
    private String injectionScheduleId;

    @Column(name = "DESCRIPTION", length = 1000)
    private String description;

    @Column(name = "END_DATE")
    private LocalDate endDate;

    @Column(name = "PLACE", length = 255)
    private String place;

    @Column(name = "START_DATE")
    private LocalDate startDate;


}
