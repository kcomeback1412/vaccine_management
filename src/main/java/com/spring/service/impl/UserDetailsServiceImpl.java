package com.spring.service.impl;

import com.spring.entities.UserDetail;
import com.spring.repositories.UserDetailRepository;
import com.spring.service.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserDetailRepository userDetailRepository;

    @Override
    public UserDetail save(UserDetail userDetail){
       return userDetailRepository.save(userDetail);
    }

    @Override
    public Page<UserDetail> findAll(Pageable pageable) {
        return userDetailRepository.findAll(pageable);
    }
}
