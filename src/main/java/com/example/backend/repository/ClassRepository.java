package com.example.backend.repository;

import com.example.backend.entity.Class;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassRepository extends JpaRepository<Class, Integer> {

    @Query(value ="select * from class where deleted = 0 and instructor_id = ?", nativeQuery = true)
    List<Class> getScheduleByInstructor(int id);

}
