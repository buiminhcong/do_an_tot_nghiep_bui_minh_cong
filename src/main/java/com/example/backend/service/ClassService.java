package com.example.backend.service;

import com.example.backend.dto.ClassRequest;
import com.example.backend.entity.Class;
import org.springframework.stereotype.Service;

@Service
public interface ClassService {

    Class createClass(ClassRequest request);

}
