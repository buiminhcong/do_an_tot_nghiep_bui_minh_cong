package com.example.backend.service.serviceImpl;

import com.example.backend.dto.CourseRequest;
import com.example.backend.entity.*;
import com.example.backend.entity.Class;
import com.example.backend.entity.Module;
import com.example.backend.exception.NotFoundException;
import com.example.backend.repository.*;
import com.example.backend.service.CourseService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private InstructorRepository instructorRepository;

    @Autowired
    private InstructorCourseRepository instructorCourseRepository;

    @Autowired
    private ModuleRepository moduleRepository;

    @Autowired
    private ClassRepository classRepository;


    @Override
    public List<Course> getListCourse() {
        return courseRepository.getListCourse();
    }

    @Override
    public Course createCourse(CourseRequest request) {

        Course course = new Course();
        course.setName(request.getName());
        course.setMaxNumbOfStudents(request.getMaxNumbOfStudents());
        Optional<Module> optional = Optional.ofNullable(moduleRepository.findModuleById(request.getModule_id()));
        if(optional.isPresent()){
            Module module = optional.get();
            Department department = module.getSubject().getDepartment();
            Subject subject = module.getSubject();
            course.setNumber("number");
            course.setNumberOfCourse(1);
            course.setDeleted(0);
            course.setDepartment(department);
            course.setModule(module);
            Course c = courseRepository.save(course);
            List<Instructor> list = instructorRepository.findListInstructorBySubject(subject.getId());
            for(Instructor i : list){
                InstructorCourse instructorCourse  = new InstructorCourse(c, i);
                instructorCourse.setDeleted(0);
                instructorCourseRepository.save(instructorCourse);
            }
            return  c;
        }
        throw new NotFoundException("Not found module id : "+ request.getModule_id() );
    }

    @Override
    public Course updateCourse(CourseRequest request, int id) throws NotFoundException{

        Course course  = courseRepository.getCourseById(id);
        Optional<Module> optional =
                Optional.ofNullable(moduleRepository.findModuleById(request.getModule_id()));
        if(course != null && optional.isPresent()){

            Module module = optional.get();
            Department department = module.getSubject().getDepartment();
            Subject subject = module.getSubject();
            course.setName(request.getName());
            course.setMaxNumbOfStudents(request.getMaxNumbOfStudents());
            course.setModule(module);
            course.setDepartment(department);
            Course c1  = courseRepository.save(course);

            List<InstructorCourse> list = instructorCourseRepository.getInstructorCourseByIdCourse(id);

            for(InstructorCourse c: list){
                instructorCourseRepository.delete(c);
            }

            List<Instructor> instructors =
                    instructorRepository.findListInstructorBySubject(subject.getId());
            for(Instructor i : instructors){
                InstructorCourse instructorCourse  = new InstructorCourse(c1, i);
                instructorCourse.setDeleted(0);
                instructorCourseRepository.save(instructorCourse);
            }
            return c1;
        }

       throw new NotFoundException("Not found course with id: "+ id);
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

            Class c = classRepository.getClassByIdCourse(course.getId());
            c.setDeleted(1);
            classRepository.save(c);

            List<InstructorCourse> list = instructorCourseRepository.getInstructorCourseByIdCourse(id);
            for(InstructorCourse i : list){
                i.setDeleted(1);
                instructorCourseRepository.save(i);
            }
            courseRepository.save(course);
            return true;
        }
        throw new NotFoundException("Not found course with id: "+ id);
    }
}
