package com.example.backend.repository;

import com.example.backend.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Integer> {

    @Query(value = "select * from subject where id = ? and deleted = 0;", nativeQuery = true)
    Subject findSubjectById(int id);

    @Query(value = "select * from subject where  deleted = 0;", nativeQuery = true)
    List<Subject> findAllSubject();


}
