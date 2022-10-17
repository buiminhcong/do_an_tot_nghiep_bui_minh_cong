package com.example.backend.controller;

import com.example.backend.dto.TeacherRequest;
import com.example.backend.entity.Instructor;
import com.example.backend.service.InstructorCourseService;
import com.example.backend.service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/instructor")
@CrossOrigin("http://localhost:4200")
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

    @PostMapping("/create")
    public Instructor createInstructor( @Valid @RequestBody TeacherRequest request){
        return instructorService.createInstructor(request);
    }

    @PostMapping("/update/{id}")
    public Instructor updateInstructor( @PathVariable("id") int id,
                                        @Valid @RequestBody TeacherRequest request){
        return instructorService.updateInstructor(id, request);
    }

    @GetMapping("/remove/{id}")
    public Boolean removeInstructor(@PathVariable("id") int id){
        return instructorService.removeInstructorById(id);
    }

}
