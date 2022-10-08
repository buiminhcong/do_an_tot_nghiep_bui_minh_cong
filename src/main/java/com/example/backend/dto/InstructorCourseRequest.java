package com.example.backend.dto;

import com.example.backend.entity.Course;
import com.example.backend.entity.Instructor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InstructorCourseRequest {

    @NotNull(message = "Course request")
    private Course course;

    @NotNull(message = "Instructor request")
    private Instructor instructor;

}
