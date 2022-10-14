package com.example.backend.repository;

import com.example.backend.entity.Role;
import com.example.backend.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    @Query(value = "select * from role where name = ? and deleted = 0", nativeQuery = true)
    Role getRoleByName(String name);

}
