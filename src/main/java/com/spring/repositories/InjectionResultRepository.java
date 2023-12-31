package com.spring.repositories;

import com.spring.entities.InjectionResult;
import com.spring.entities.Users;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InjectionResultRepository extends JpaRepository<InjectionResult, String> {

    @Transactional(rollbackOn = {Exception.class, Throwable.class})
    public Integer countAllByUsers3(Users users);

    @Transactional(rollbackOn = {Exception.class, Throwable.class})
    @Query("SELECT COUNT(I) FROM InjectionResult AS I" )
    public Integer countAll();

    @Transactional(rollbackOn = {Exception.class, Throwable.class})
    @Query("SELECT I FROM InjectionResult AS I WHERE I.prevention.preventionName like %?1%" )
    public List<InjectionResult> findAllByPreventionNameLike(String preventionName);

    @Modifying
    @Transactional(rollbackOn = {Exception.class, Throwable.class})
    @Query("DELETE FROM InjectionResult  AS I WHERE I.injectionResultId IN(?1)")
    public void deleteInjectionResultByListId(List<String> listId);

    @Query("DELETE FROM InjectionResult AS I WHERE I.users3.usersId = ?1")
    public void deleteAllByCustomerID(Integer userID);

    public List<InjectionResult> findAllByUsers3(Users user);

    @Transactional(rollbackOn = {Exception.class, Throwable.class})
    @Query("SELECT I FROM InjectionResult AS I WHERE I.users3 = ?1 AND I.prevention.preventionName like %?2%" )
    public List<InjectionResult> findAllByUsers3PreventionNameLike(Users users, String preventionName);
}
