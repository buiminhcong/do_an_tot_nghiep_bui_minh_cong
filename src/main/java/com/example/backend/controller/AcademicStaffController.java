package com.example.backend.controller;

import com.example.backend.entity.Course;
import com.example.backend.entity.Department;
import com.example.backend.entity.ga.Schedule;
import com.example.backend.repository.DepartmentRepository;
import com.example.backend.service.CourseService;
import com.example.backend.service.DepartmentService;
import com.example.backend.service.InstructorCourseService;
import com.example.backend.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/course")
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

    @GetMapping("/d")
    public List<Department> getAllCourse(){
        return departmentService.getListDepartment();
    }

    @GetMapping("/ok")
    public Schedule gaSchedule(){
        return scheduleService.gaSchedule();
    }

}
