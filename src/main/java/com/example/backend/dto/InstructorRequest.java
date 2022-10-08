package com.example.backend.dto;

import com.example.backend.entity.Subject;
import com.example.backend.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InstructorRequest {

    @NotBlank(message = "Code request")
    private String code;

    @NotBlank(message = "Subject request")
    private Subject subject;

    @NotBlank(message = "User request")
    private User user;

}
