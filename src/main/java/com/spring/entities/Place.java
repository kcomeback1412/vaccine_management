package com.spring.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Entity
public class Place implements Serializable {
    @Id
    @Column(name="PLACE_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer placeId;

    @Column(name = "PLACE_NAME", length = 255)
    private String placeName;
    
    @Column(name = "PLACE_ADRESS", length = 255)
    private String placeAdress;
    @OneToOne
    @JoinColumn(name = "INJECTION_RESULT_ID")
    private InjectionResult injectionResult2;

}
