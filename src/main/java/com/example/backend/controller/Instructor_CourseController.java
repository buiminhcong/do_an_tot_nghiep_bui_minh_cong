package com.example.backend.controller;

import com.example.backend.entity.InstructorCourse;
import com.example.backend.repository.InstructorCourseRepository;
import com.example.backend.service.InstructorCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/ic")
public class Instructor_CourseController {

    @Autowired
    private InstructorCourseService instructorCourseService;



    @GetMapping("/{id}")
    public List<InstructorCourse> getListInstructorCourseByIdCourse(@PathVariable("id") int id) {

        return instructorCourseService.getInstructorCourseByIdCourse(id);

    }

}
