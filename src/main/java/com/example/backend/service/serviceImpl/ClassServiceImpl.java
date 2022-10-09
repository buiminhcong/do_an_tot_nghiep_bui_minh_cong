package com.example.backend.service.serviceImpl;

import com.example.backend.dto.ClassRequest;
import com.example.backend.entity.Class;
import com.example.backend.repository.ClassRepository;
import com.example.backend.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClassServiceImpl implements ClassService {

    @Autowired
    private ClassRepository classRepository;

    @Override
    public Class createClass(ClassRequest request) {

        Class c = new Class(request.getDept(), request.getCourse(), request.getInstructor()
                , request.getMeetingTime(), request.getRoom(), request.getSchedule());
        return classRepository.save(c);
    }
}
