package com.example.backend.repository;

import com.example.backend.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {

    @Query(value = "select * from user_role where user_id = ? and deleted = 0;", nativeQuery = true)
    UserRole getUserRoleByIdUser(int id);

}
