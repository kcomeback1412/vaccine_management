package com.spring.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Getter
@Setter
public class UserRoles implements Serializable {

    @EmbeddedId
    private UserRolesId id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "USER_ID")
    private Users users1;

    @ManyToOne
    @MapsId("roleId")
    @JoinColumn(name = "roles_id")
    private Roles roles;

}
