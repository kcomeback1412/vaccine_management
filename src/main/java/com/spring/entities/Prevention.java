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
public class Prevention implements Serializable {
    @Id
    @Column(name="PREVENTION_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer preventionId;

    @Column(name = "PREVENTION_NAME", length = 255)
    private String preventionName;

    @OneToMany(mappedBy = "prevention")
    private List<InjectionResult>  injectionResults;

    public void addInjectionResult(InjectionResult injectionResult) {
        if(injectionResults == null) {
            injectionResults = new ArrayList<>();
        }
        injectionResults.add(injectionResult);
    }
}
