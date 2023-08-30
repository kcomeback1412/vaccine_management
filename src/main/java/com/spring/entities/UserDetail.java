package com.spring.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@Entity
public class UserDetail implements Serializable {
    @Id
    @Column(name = "ID", length = 12, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

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

    @Column(name = "PHONE", length = 20)
    private String phone;

    // id of customer
    @Column(name = "IDENTITY_CARD", length = 12)
    private String identityCard;

    // employee
    @Column(name = "IMAGE", length = 255)
    private String image;

    @Column(name = "POSITION", length = 100)
    private String position;

    @Column(name = "WORKING_PLACE", length = 255)
    private String workingPlace;

    @OneToOne
    @JoinColumn(name = "USERS_ID")
    private Users users2;

}
