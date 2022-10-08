package com.example.backend.service.serviceImpl;

import com.example.backend.entity.Course;
import com.example.backend.exception.NotFoundException;
import com.example.backend.repository.CourseRepository;
import com.example.backend.service.CourseService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public List<Course> getListCourse() {
        return courseRepository.getListCourse();
    }

    @Override
    public Course getCourseById(int id) throws NotFoundException {
        Optional<Course> optional = Optional.ofNullable(courseRepository.getCourseById(id));
        if(optional.isPresent()){
            return optional.get();
        }
        throw new NotFoundException("Not found course with id: "+ id);
    }

    @Override
    public Boolean removeCourseById(int id) throws NotFoundException{
        Optional<Course> optional = Optional.ofNullable(courseRepository.getCourseById(id));
        if(optional.isPresent()){
            Course course = optional.get();
            course.setDeleted(1);
            courseRepository.save(course);
            return true;
        }
        throw new NotFoundException("Not found course with id: "+ id);
    }
}
