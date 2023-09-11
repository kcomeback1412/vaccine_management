package com.spring.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
    
    @Column(name = "PLACE_ADDRESS", length = 255)
    private String placeAddress;

    @OneToMany(mappedBy = "place")
    private List<InjectionResult>  injectionResults;

    public void addInjectionResult(InjectionResult injectionResult) {
        if(injectionResults == null) {
            injectionResults = new ArrayList<>();
        }
        injectionResults.add(injectionResult);
    }

}
