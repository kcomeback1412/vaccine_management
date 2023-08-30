package com.spring.consts;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RoleEnum {
    ADMIN(1, "ADMIN"),     //You must  insert "ROLE_ADMIN" to database with id = 1
    STAFF(2, "STAFF"),     //You must  insert "ROLE_STAFF" to database with id = 2
    USER(3, "USER"),       //You must  insert "ROLE_USER" to database with id = 3
    ;
    private int id;
    private String name;
}
