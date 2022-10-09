package com.example.backend.service;

import com.example.backend.dto.CourseRequest;
import com.example.backend.entity.Course;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CourseService {

    List<Course> getListCourse();

    Course createCourse(CourseRequest request);

    Course updateCourse(CourseRequest request, int id);
    Course getCourseById(int id);

    Boolean removeCourseById(int id);

}
