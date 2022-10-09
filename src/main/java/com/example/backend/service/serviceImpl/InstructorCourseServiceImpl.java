package com.example.backend.service.serviceImpl;

import com.example.backend.dto.InstructorCourseRequest;
import com.example.backend.entity.InstructorCourse;
import com.example.backend.exception.NotFoundException;
import com.example.backend.repository.InstructorCourseRepository;
import com.example.backend.service.InstructorCourseService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InstructorCourseServiceImpl implements InstructorCourseService {

    @Autowired
    private InstructorCourseRepository instructorCourseRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public List<InstructorCourse> getInstructorCourseByIdCourse(int id_course) throws NotFoundException {
        List<InstructorCourse> list = instructorCourseRepository.getInstructorCourseByIdCourse(id_course);
        if (list != null) {
            return list;
        } else {
            throw new NotFoundException("Not found instructor course with id course = " + id_course);
        }
    }

    @Override
    public List<InstructorCourse> getListInstructorCourse() {
        return instructorCourseRepository.getListCourseInstructor();
    }

    @Override
    public InstructorCourse createInstructorCourse(InstructorCourseRequest request) {
        InstructorCourse instructorCourse = mapper.map(request, InstructorCourse.class);
        instructorCourse.setDeleted(0);
        return instructorCourseRepository.save(instructorCourse);
    }

    @Override
    public InstructorCourse updateInstructorCourse(int id, InstructorCourseRequest request) throws NotFoundException {

        Optional<InstructorCourse> optional = instructorCourseRepository.findById(id);
        if (optional.isPresent()) {
            InstructorCourse course = optional.get();
            course.setCourse(request.getCourse());
            course.setInstructor(request.getInstructor());
            return instructorCourseRepository.save(course);
        }

        throw new NotFoundException("Not found course with id: " + id);
    }


    @Override
    public Boolean removeInstructorCourseByIdCourse(int id_course) throws NotFoundException {
        List<InstructorCourse> list =
                instructorCourseRepository.getInstructorCourseByIdCourse(id_course);
        if (list != null) {
            for (InstructorCourse course: list
                 ){
                course.setDeleted(1);
            }
            return true;
        }
        throw new NotFoundException("Course not found with id "+id_course );
    }
}
