package com.example.backend.service;

import com.example.backend.dto.InstructorRequest;
import com.example.backend.entity.Instructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface InstructorService {

    List<Instructor> getListInstructor();

    Instructor createInstructor(InstructorRequest instructorRequest);

    Instructor updateInstructor(int id, InstructorRequest instructorRequest);

    Instructor findInstructorByCode(String code);

    Instructor removeInstructorById(int id);

    List<Instructor> getListInstructorByIdCourse(int id_course);

}
