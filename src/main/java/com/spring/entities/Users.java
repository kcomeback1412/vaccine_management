package com.spring.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Entity
public class Users implements Serializable {
    @Id
    @Column(name="USERS_ID")
    private String usersId;

    @Column(name = "USERNAME", length = 255)
    private String userName;

    @Column(name = "PASSWORD", length = 255)
    private String password;

    @OneToMany(mappedBy = "users1")
    private List<UserRoles> userRoles;

    @OneToOne(mappedBy = "users2")
    private UserDetail userDetail;


    @OneToMany(mappedBy = "users3")
    private List<InjectionResult> injectionResultList1;
}
