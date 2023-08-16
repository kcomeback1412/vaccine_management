package com.spring.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@Entity
public class Employee implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EMPLOYEE_ID", length = 36)
    private String employeeId;

    @Column(name = "ADDRESS", length = 255)
    private String address;

    @Column(name = "DATE_OF_BIRTH")
    private LocalDate dateOfBirth;

    @Column(name = "EMAIL", length = 100)
    private String email;

    @Column(name = "EMPLOYEE_NAME", length = 100)
    private String employeeName;

    @Column(name = "GENDER")
    private Integer gender;

    @Column(name = "IMAGE", length = 255)
    private String image;

    @Column(name = "PASSWORD", length = 255)
    private String password;

    @Column(name = "PHONE", length = 20)
    private String phone;

    @Column(name = "POSITION", length = 100)
    private String position;

    @Column(name = "USERNAME", length = 255)
    private String username;

    @Column(name = "WORKING_PLACE", length = 255)
    private String workingPlace;
}
