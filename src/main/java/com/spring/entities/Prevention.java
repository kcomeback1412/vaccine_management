package com.spring.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Entity
public class Prevention implements Serializable {
    @Id
    @Column(name="PREVENTION_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer preventionId;

    @Column(name = "PREVENTION_NAME", length = 255)
    private String preventionName;

    @OneToOne
    @JoinColumn(name = "INJECTION_RESULT_ID")
    private InjectionResult injectionResult1;

}
