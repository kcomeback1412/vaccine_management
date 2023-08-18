package com.spring.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
public class Customer2  implements Serializable {
    @Id
    @Column(name = "CUSTOMER_ID", length = 36)
    private String customerId;

    @Column(name = "ADDRESS", length = 255)
    private String address;

    @Column(name = "DATE_OF_BIRTH")
    private LocalDate dateOfBirth;

    @Column(name = "EMAIL", length = 100)
    private String email;

    @Column(name = "FULL_NAME", length = 100)
    private String fullName;

    @Column(name = "GENDER")
    private Integer gender;

    @Column(name = "IDENTITY_CARD", length = 12)
    private String identityCard;

    @Column(name = "PASSWORD", length = 255)
    private String password;

    @Column(name = "PHONE", length = 20)
    private String phone;

    @Column(name = "USERNAME", length = 255)
    private String userName;

    @OneToMany(mappedBy = "customer")
    private List<InjectionResult> injectionResultList1;

}
