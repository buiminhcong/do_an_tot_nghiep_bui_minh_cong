package com.example.backend.repository;

import com.example.backend.entity.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InstructorRepository extends JpaRepository<Instructor, Integer> {

    @Query(value = "select * from instructor where deleted = 0", nativeQuery = true)
    List<Instructor> findAllInstructor();

    @Query(value = "select * from instructor where deleted = 0 and code = ? ", nativeQuery = true)
    Instructor findInstructorByCode(String code);

    @Query(value = "select * from instructor where deleted = 0 and id = ? ", nativeQuery = true)
    Instructor findInstructorByID(int  id);

    @Query(value = "select * from instructor where subject_id = ? and deleted = 0;", nativeQuery = true)
    List<Instructor> findListInstructorBySubject(int subject_id);


    @Query(value = " select * from instructor where user_id = ? ", nativeQuery = true)
    Instructor findInstructorByUserId(int id);
}
