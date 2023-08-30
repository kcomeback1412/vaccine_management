package com.spring.service;

import com.spring.entities.UserDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserDetailsService {
    UserDetail save(UserDetail userDetail);

    Page<UserDetail> findAll(Pageable pageable);
}
