package com.example.backend.service.serviceImpl;

import com.example.backend.entity.Course;
import com.example.backend.entity.Department;
import com.example.backend.repository.DepartmentRepository;
import com.example.backend.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DeparmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public List<Department> getListDepartment() {
        List<Department> departmentList =
                departmentRepository.getListDepartment();
        List<Department> list = new ArrayList<>();

        for(Department d : departmentList){
            List<Course> courses = d.getCourses();
            List<Course> cResult = new ArrayList<>();
            for(Course c : courses){
                if(c.getDeleted() == 0){
                    cResult.add(c);
                }
            }
            d.setCourses(cResult);
            list.add(d);
        }
        return list;
    }
}
