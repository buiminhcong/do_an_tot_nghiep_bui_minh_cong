package com.example.backend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "instructor_course")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InstructorCourse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private Course course;

    @ManyToOne
    private  Instructor instructor;

    private int deleted;

}
