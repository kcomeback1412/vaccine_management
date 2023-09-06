package com.spring.repositories;



import com.spring.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {
	
	public Users findByUserName(String userName);
	
	@Query("SELECT u FROM Users u WHERE u.id = ?1")
	public Users findByIdUser(String id);
	
}
