package com.spring.service;

import com.spring.entities.InjectionResult;
import com.spring.entities.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface InjectionResultService {
    InjectionResult save(InjectionResult injectionResult);

    Integer countAllInjectionResult();

    Integer countAllInjectionResultByUser(Users users);

    List<InjectionResult> findAll();

    List<InjectionResult> findAllByPreventionNameLike(String preventionName);

    Page<InjectionResult> convertListInjectionResultToPageInjectionResult(Pageable pageable, List<InjectionResult> injectionResultList);

    void deleteInjectionResultByListId(List<String> listId);

    Optional<InjectionResult> findByID(String id);

    void deleteAllByCustomerID(Integer id);
}
