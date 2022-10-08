package com.example.backend.service;

import com.example.backend.entity.Course;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CourseService {

    List<Course> getListCourse();

    Course getCourseById(int id);

    Boolean removeCourseById(int id);

}
