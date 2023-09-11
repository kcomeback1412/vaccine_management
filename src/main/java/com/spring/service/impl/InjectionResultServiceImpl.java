package com.spring.service.impl;

import com.spring.entities.InjectionResult;
import com.spring.entities.Users;
import com.spring.repositories.InjectionResultRepository;
import com.spring.service.InjectionResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InjectionResultServiceImpl implements InjectionResultService {
    @Autowired
    private InjectionResultRepository injectionResultRepository;


    @Override
    public InjectionResult save(InjectionResult injectionResult){
        return injectionResultRepository.save(injectionResult);
    }

    @Override
    public Integer countAllInjectionResult(){
        return injectionResultRepository.countAll();
    }

    @Override
    public Integer countAllInjectionResultByUser(Users users) {
        return injectionResultRepository.countAllByUsers3(users);
    }

    @Override
    public List<InjectionResult> findAll() {
        return injectionResultRepository.findAll();
    }

    @Override
    public  List<InjectionResult> findAllByPreventionNameLike(String preventionName){
       return injectionResultRepository.findAllByPreventionNameLike(preventionName);
    }

    @Override
    public Page<InjectionResult> convertListInjectionResultToPageInjectionResult(Pageable pageable, List<InjectionResult> injectionResultList) {
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), injectionResultList.size());
        List<InjectionResult> pageContent = injectionResultList.subList(start, end);
        return new PageImpl<>(pageContent, pageable, injectionResultList.size());
    }

}
