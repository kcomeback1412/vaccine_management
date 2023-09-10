package com.spring.repositories;



import com.spring.entities.Users;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {
	
	public Users findByUserName(String userName);
	
	@Query("SELECT u FROM Users u WHERE u.usersId = ?1")
	public Users findByIdUser(String id);

	@Modifying
	@Transactional(rollbackOn = {Exception.class, Throwable.class})
	@Query("DELETE FROM Users AS U WHERE U.usersId IN (?1)")
	public void deleteUserByListId(List<Integer> listId) ;
	
}
