package com.spring.service.impl;

import com.spring.repositories.UserDetailRepository;
import com.spring.service.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserDetailRepository userDetailRepository;
}
