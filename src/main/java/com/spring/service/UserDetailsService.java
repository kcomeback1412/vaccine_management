package com.spring.service;

import com.spring.entities.UserDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface UserDetailsService {
    UserDetail save(UserDetail userDetail);

    Optional<UserDetail> findById(Integer id);

    Page<UserDetail> findAll(Pageable pageable);

    List<UserDetail> findAllEmployee();
    
    List<UserDetail> findAllByFullName(String name);

    Page<UserDetail> convertListUserDetailToPageUserDetail(Pageable pageable, List<UserDetail> userDetails);


    void deleteEmployee(List<Integer> listId);

    Integer countAllEmployee();
}
