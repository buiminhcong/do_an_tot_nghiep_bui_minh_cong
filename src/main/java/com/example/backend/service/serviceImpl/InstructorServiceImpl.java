package com.example.backend.service.serviceImpl;

import com.example.backend.dto.InstructorRequest;
import com.example.backend.entity.Instructor;
import com.example.backend.entity.InstructorCourse;
import com.example.backend.exception.AlreadyExistException;
import com.example.backend.exception.NotFoundException;
import com.example.backend.repository.InstructorCourseRepository;
import com.example.backend.repository.InstructorRepository;
import com.example.backend.service.InstructorCourseService;
import com.example.backend.service.InstructorService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InstructorServiceImpl implements InstructorService {
    @Autowired
    private  InstructorRepository repository;
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private  InstructorCourseRepository instructorCourseRepository ;

//    public InstructorServiceImpl(InstructorCourseRepository instructorCourseRepository){
//        this.instructorCourseRepository = instructorCourseRepository;
//    }


    @Override
    public List<Instructor> getListInstructor() {
        List<Instructor> list = repository.findAllInstructor();
        return list;
    }

    @Override
    public Instructor createInstructor(InstructorRequest instructorRequest)
            throws AlreadyExistException {

        Instructor instructor = findInstructorByCode(instructorRequest.getCode());
        if (instructor == null) {
            Instructor i = mapper.map(instructorRequest, Instructor.class);
            i.setDeleted(0);
            return i;
        } else {
            throw new AlreadyExistException("Instructor is exist");
        }
    }

    @Override
    public Instructor updateInstructor(int id, InstructorRequest instructorRequest)
            throws NotFoundException {

        Optional<Instructor> i = repository.findById(id);
        if (i.isPresent()) {
            Instructor instructor = i.get();
            instructor.setUser(instructorRequest.getUser());
            instructor.setSubject(instructorRequest.getSubject());
            return repository.save(instructor);
        }
        throw new NotFoundException("Not found Instructor with id: " + id);

    }

    @Override
    public Instructor findInstructorByCode(String code) throws NotFoundException {

        Instructor i = repository.findInstructorByCode(code);
        if (i != null) {
            return i;
        } else {
            throw new NotFoundException("Not found Instructor with code : " + code);
        }
    }

    @Override
    public Instructor removeInstructorById(int id) throws NotFoundException {

        Optional<Instructor> i = repository.findById(id);
        if (i.isPresent()) {
            return i.get();
        } else {
            throw new NotFoundException("Not found Instructor with code : " + id);
        }
    }

    @Override
    public List<Instructor> getListInstructorByIdCourse(int id_course) throws NotFoundException{

        List<Instructor> instructors = new ArrayList<>();
        List<InstructorCourse> list = instructorCourseRepository.getInstructorCourseByIdCourse(id_course);

        if(list != null){
            for (int i=0; i<list.size(); i++){
                instructors.add(list.get(i).getInstructor());
            }
            return instructors;
        }
        throw new NotFoundException("Not found instructor in course with id "+ id_course );
    }

    @Override
    public List<Instructor> getListInstructorByIdSubject(int id_subject) {
        return repository.findListInstructorBySubject(id_subject);
    }
}
