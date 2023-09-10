package com.spring.entities;

import com.spring.consts.RoleEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@Entity
public class Users implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    @Column(name="USERS_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer usersId;

    @Column(name = "USERNAME", length = 255)
    private String userName;

    @Column(name = "PASSWORD", length = 255)
    private String password;

    @Column(name = "ROLE", length = 36)
    @Enumerated(EnumType.STRING)
    private RoleEnum roleEnum;

    @OneToOne(mappedBy = "users2")
    private UserDetail userDetail;

    @OneToMany(mappedBy = "users3")
    private List<InjectionResult> injectionResults;

    public void addInjectionResult(InjectionResult injectionResult) {
        if(injectionResults == null) {
            injectionResults = new ArrayList<>();
        }
        injectionResults.add(injectionResult);
    }
}
