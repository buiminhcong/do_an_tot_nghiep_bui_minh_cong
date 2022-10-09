package com.example.backend.repository;

import com.example.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(value = "select * from user where user_name = ? and deleted = 0;", nativeQuery = true)
    User findUserByUserName(String username);

    @Query(value = "select * from user where user_name = ?1 and password = ?2  and deleted = 0;",
            nativeQuery = true)
    User findUserByUserNameAndPassword(String username, String password);
}
