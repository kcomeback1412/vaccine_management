package com.spring.repositories;

import com.spring.entities.UserRoles;
import com.spring.entities.UserRolesId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRolesRepository extends JpaRepository<UserRoles, UserRolesId> {
}
