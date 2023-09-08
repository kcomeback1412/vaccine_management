package com.spring.repositories;


import com.spring.consts.RoleEnum;
import com.spring.entities.UserDetail;

import jakarta.transaction.Transactional;

import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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
	@Query("SELECT U FROM UserDetail AS U WHERE U.users2.roleEnum = ?1 AND U.fullName like %?2%")
	public List<UserDetail> findAllByUsers2RoleEnumAndFullNameLike(RoleEnum role,String fullName);

	@Transactional(rollbackOn = {Exception.class, Throwable.class})
	public Integer countAllByUsers2RoleEnum(RoleEnum roleEmployee);
	
	@Query("SELECT u FROM UserDetail u JOIN Users us on u.id = us.usersId WHERE u.fullName like %:name% AND us.roleEnum = :role")
	public Page<UserDetail> findUserDetailCustomerWithPagin(@Param("name") String name,@Param("role") RoleEnum role , Pageable pageable);
	
	@Query("SELECT u FROM UserDetail u JOIN Users us on u.id = us.usersId WHERE us.roleEnum = ?1 ")
	public Page<UserDetail> findAllCustomerByRole(Pageable pageable, RoleEnum role);

	
	

	
}
