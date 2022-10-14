package com.example.backend.service.serviceImpl;

import com.example.backend.entity.Subject;
import com.example.backend.exception.NotFoundException;
import com.example.backend.repository.SubjectRepository;
import com.example.backend.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    @Override
    public Subject getSubjectById(int id) throws NotFoundException {

        Optional<Subject> optional = Optional.ofNullable(subjectRepository.findSubjectById(id));
        if(optional.isPresent()){
            return optional.get();
        }
        throw new NotFoundException("Not found subject with id: "+ id);
    }

    @Override
    public List<Subject> getAllSubject() {
        return subjectRepository.findAllSubject();
    }

    @Override
    public Boolean removeSubject(int id) throws NotFoundException{
        Optional<Subject> optional = Optional.ofNullable(subjectRepository.findSubjectById(id));
        if(optional.isPresent()){
            Subject subject = optional.get();
            subject.setDeleted(1);
            subjectRepository.save(subject);
            return true;
        }
        throw new NotFoundException("Not found subject with id: "+ id);
    }
}
