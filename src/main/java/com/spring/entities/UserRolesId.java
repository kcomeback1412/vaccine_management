package com.spring.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
public class UserRolesId implements Serializable {

    @Column(name = "roles_id")
    private Integer roleId;

    @Column(name = "USER_ID")
    private String userId;

}
