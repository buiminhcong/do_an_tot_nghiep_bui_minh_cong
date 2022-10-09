package com.example.backend.controller;

import com.example.backend.dto.InstructorRequest;
import com.example.backend.entity.Instructor;
import com.example.backend.service.InstructorCourseService;
import com.example.backend.service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/instructor")
public class InstructorController {

    @Autowired
    private InstructorService instructorService;

    @Autowired
    private InstructorCourseService instructorCourseService;

    @GetMapping("")
    public List<Instructor> getListInstructor(){
        return instructorService.getListInstructor();
    }

    @GetMapping("/{id}")
    public List<Instructor> getListInstructorByCourseId( @PathVariable("id") int id_course){
        return instructorService.getListInstructorByIdCourse(id_course);
    }



}
