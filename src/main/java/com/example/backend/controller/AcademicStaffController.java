package com.example.backend.controller;

import com.example.backend.dto.CourseRequest;
import com.example.backend.entity.Course;
import com.example.backend.entity.Department;
import com.example.backend.entity.Instructor;
import com.example.backend.entity.ga.Schedule;
import com.example.backend.repository.DepartmentRepository;
import com.example.backend.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/course")
@CrossOrigin("http://localhost:4200")
public class AcademicStaffController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private InstructorCourseService instructorCourseService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private ScheduleService scheduleService;

    @GetMapping("")
    public List<Course> getListCourse(){
        return courseService.getListCourse();
    }

    @GetMapping("/{id}")
    public Course getCourseById  ( @PathVariable("id") int id){
        return courseService.getCourseById(id);
    }

    @GetMapping("/department")
    public List<Department> getAllCourse(){
        return departmentService.getListDepartment();
    }

    @GetMapping("/ok")
    public Schedule gaSchedule(){
        return scheduleService.gaSchedule();
    }
    @PostMapping("/create")
    public Course createCourse( @RequestBody CourseRequest request){
        return courseService.createCourse(request);
    }

    @GetMapping("/remove/{id}")
    public Boolean removeCourse( @PathVariable("id") int id ){
        return courseService.removeCourseById(id);
    }

    @PostMapping("/update/{id}")
    public Course updateCourse(@PathVariable("id") int id, @RequestBody CourseRequest request){
        return courseService.updateCourse(request, id);
    }

}
