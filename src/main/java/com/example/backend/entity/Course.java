package com.example.backend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String number;

    private int maxNumbOfStudents;

    private int deleted;

    private int numberOfCourse;

    @OneToOne
    private Module module;

    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    @JsonIgnore
    private Department department;

    @OneToMany(mappedBy = "course", fetch = FetchType.EAGER)
    @JsonIgnore
    private List<InstructorCourse> instructorCourses;

}
