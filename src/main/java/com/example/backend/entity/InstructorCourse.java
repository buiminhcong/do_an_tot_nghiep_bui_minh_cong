package com.example.backend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

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

    public InstructorCourse(Course course, Instructor instructor) {
        this.course = course;
        this.instructor = instructor;
    }
}
