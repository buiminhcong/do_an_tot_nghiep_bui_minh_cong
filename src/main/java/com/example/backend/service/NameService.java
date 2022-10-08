package com.example.backend.service;

import com.example.backend.dto.NameRequest;
import com.example.backend.entity.Name;
import org.springframework.stereotype.Service;

@Service
public interface NameService {

    Name createName(NameRequest request);

    Name updateName(int id, NameRequest request);

    Boolean removeName(int id);

}
