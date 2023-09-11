package com.spring.service;

import com.spring.entities.Users;

import java.util.List;

public interface UsersService {
    Users save(Users user);

    void deleteUserByListId(List<Integer> listID);

    Users findByUsername(String username);
}
