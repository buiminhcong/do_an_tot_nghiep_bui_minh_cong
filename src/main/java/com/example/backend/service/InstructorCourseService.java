package com.example.backend.service;

import com.example.backend.dto.InstructorCourseRequest;
import com.example.backend.entity.InstructorCourse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface InstructorCourseService {

    List<InstructorCourse> getInstructorCourseByIdCourse(int id_course);

    List<InstructorCourse> getListInstructorCourse();

    InstructorCourse createInstructorCourse(InstructorCourseRequest request);

    InstructorCourse updateInstructorCourse(int id, InstructorCourseRequest request);

    Boolean removeInstructorCourseByIdCourse(int id);



}
