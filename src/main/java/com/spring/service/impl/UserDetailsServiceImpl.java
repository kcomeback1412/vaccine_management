package com.spring.service.impl;

import com.spring.consts.RoleEnum;
import com.spring.entities.UserDetail;
import com.spring.repositories.UserDetailRepository;
import com.spring.service.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserDetailRepository userDetailRepository;

    @Override
    public UserDetail save(UserDetail userDetail){
       return userDetailRepository.save(userDetail);
    }

    @Override
    public Optional<UserDetail> findById(Integer id) {
        return userDetailRepository.findById(id);
    }

    @Override
    public Page<UserDetail> findAll(Pageable pageable) {
        return userDetailRepository.findAll(pageable);
    }

    @Override
    public List<UserDetail> findAllEmployee(){
        return userDetailRepository.findAllByUsers2RoleEnum(RoleEnum.EMPLOYEE);
    }

    @Override
    public List<UserDetail> findAllByFullName(String name){
        return userDetailRepository.findAllByFullNameLike(name);
    }

    @Override
    public Page<UserDetail> convertListUserDetailToPageUserDetail(Pageable pageable, List<UserDetail> userDetails) {
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), userDetails.size());
        List<UserDetail> pageContent = userDetails.subList(start, end);
        return new PageImpl<>(pageContent, pageable, userDetails.size());
    }

    @Override
    public void deleteEmployee(List<Integer> listId) {
        userDetailRepository.deleteUserDetailByListId(listId);
    }

    @Override
    public Integer countAllEmployee(){
       return userDetailRepository.countAllByUsers2RoleEnum(RoleEnum.EMPLOYEE);
    }
}
