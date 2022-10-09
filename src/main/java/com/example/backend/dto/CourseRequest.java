package com.example.backend.dto;

import com.example.backend.entity.Module;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.OneToOne;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseRequest {

    private String name;

    private int maxNumbOfStudents;

    private int module_id;

}
