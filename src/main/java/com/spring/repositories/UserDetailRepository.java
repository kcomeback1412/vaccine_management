package com.spring.repositories;


import com.spring.entities.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailRepository extends JpaRepository<UserDetail, Integer> {
	
	@Query("SELECT u FROM UserDetail u WHERE u.id = ?1")
	public UserDetail findByIdUserDetail(String id);
	
}
