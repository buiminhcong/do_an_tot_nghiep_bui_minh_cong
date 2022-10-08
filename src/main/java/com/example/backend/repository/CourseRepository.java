package com.example.backend.repository;

import com.example.backend.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {

    @Query(value = "select * from course where deleted = 0", nativeQuery = true)
    List<Course> getListCourse();

    @Query(value = "select * from course where deleted = 0 and id = ?", nativeQuery = true)
    Course getCourseById(int id);

}
