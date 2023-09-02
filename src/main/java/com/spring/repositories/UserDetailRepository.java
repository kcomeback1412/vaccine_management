package com.spring.repositories;


import com.spring.entities.UserDetail;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailRepository extends JpaRepository<UserDetail, Integer> {
	
	@Query("SELECT u FROM UserDetail u WHERE u.id = ?1")
	public UserDetail findByIdUserDetail(String id);
	
	@Query("SELECT u FROM UserDetail u WHERE u.fullName = :name")
	public Page<UserDetail> findUserDetailWithPagin(@Param("name") String name,  Pageable pageable);
	
	
}
