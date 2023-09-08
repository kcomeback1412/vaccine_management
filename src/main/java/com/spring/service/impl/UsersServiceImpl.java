package com.spring.service.impl;

import com.spring.entities.Users;
import com.spring.repositories.UsersRepository;
import com.spring.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersServiceImpl implements UsersService {
    @Autowired
    UsersRepository usersRepository;

    @Override
    public Users save(Users user){
        return usersRepository.save(user);
    }
}