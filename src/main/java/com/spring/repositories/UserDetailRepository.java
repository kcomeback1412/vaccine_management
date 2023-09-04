package com.spring.repositories;


import com.spring.consts.RoleEnum;
import com.spring.entities.UserDetail;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDetailRepository extends JpaRepository<UserDetail, Integer> {
	
	@Query("SELECT u FROM UserDetail u WHERE u.id = ?1")
	public UserDetail findByIdUserDetail(String id);


	@Transactional(rollbackOn = {Exception.class, Throwable.class})
	public List<UserDetail> findAllByUsers2RoleEnum(RoleEnum role);

	@Modifying
	@Transactional(rollbackOn = {Exception.class, Throwable.class})
	@Query("DELETE FROM UserDetail AS U WHERE U.id IN (?1)")
	public void deleteUserDetailByListId(List<Integer> listId) ;

	@Transactional(rollbackOn = {Exception.class, Throwable.class})
	@Query("SELECT U FROM UserDetail AS U WHERE U.fullName like %?1%")
	public List<UserDetail> findAllByFullNameLike(String fullName);

	@Transactional(rollbackOn = {Exception.class, Throwable.class})
	public Integer countAllByUsers2RoleEnum(RoleEnum roleEmployee);


	
}
