package com.spring.service.impl;

import com.spring.repositories.UserDetailRepository;
import com.spring.repositories.UsersRepository;
import com.spring.service.UserDetailsService;
import com.spring.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersServiceImpl implements UsersService {
    @Autowired
    UsersRepository usersRepository;
}
