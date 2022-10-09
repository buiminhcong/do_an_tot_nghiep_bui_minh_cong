package com.example.backend.service;

import com.example.backend.entity.Department;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DepartmentService {

    List<Department> getListDepartment();


}
