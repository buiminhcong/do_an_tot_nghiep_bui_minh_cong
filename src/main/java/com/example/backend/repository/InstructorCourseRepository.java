package com.example.backend.repository;

import com.example.backend.entity.InstructorCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InstructorCourseRepository extends JpaRepository<InstructorCourse, Integer> {

    @Query(value = "select * from instructor_course where course_id = ?  and deleted = 0",
            nativeQuery = true)
    List<InstructorCourse> getInstructorCourseByIdCourse(int id_course);

    @Query(value = "select * from instructor_course where deleted = 0", nativeQuery = true)
    List<InstructorCourse> getListCourseInstructor();

}
