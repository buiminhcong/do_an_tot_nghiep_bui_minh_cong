package com.example.backend.service;

import com.example.backend.entity.Subject;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SubjectService {

    Subject getSubjectById(int id);

    List<Subject> getAllSubject();

    Boolean removeSubject(int id);

}
