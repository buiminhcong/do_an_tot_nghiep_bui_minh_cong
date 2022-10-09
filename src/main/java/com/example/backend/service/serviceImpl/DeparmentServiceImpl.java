package com.example.backend.service.serviceImpl;

import com.example.backend.entity.Department;
import com.example.backend.repository.DepartmentRepository;
import com.example.backend.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeparmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public List<Department> getListDepartment() {
        return departmentRepository.getListDepartment();
    }
}
