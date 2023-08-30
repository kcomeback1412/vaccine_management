package com.spring.service.impl;

import com.spring.entities.UserRoles;
import com.spring.repositories.UserRolesRepository;
import com.spring.service.UserRolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRolesServiceImpl implements UserRolesService {
    @Autowired
    UserRolesRepository userRolesRepository;

    @Override
    public UserRoles save(UserRoles userRoles){
        return userRolesRepository.save(userRoles);
    }
}
