package com.example.backend.dto;

import com.example.backend.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherRequest {

    @NotBlank(message = "Code request")
    private String code;

    private Integer id_subject;

    @NotBlank(message = "username request")
    private String username;

    @NotBlank(message = "password request")
    private String password;

    @NotBlank(message = "firstName request")
    private String firstName;

    @NotBlank(message = "lastName request")
    private String lastName;

    @NotBlank(message = "middleName request")
    private String middleName;

    @NotBlank(message = "phone request")
    private String phone;

    @NotBlank(message = "email request")
    private String email;

    @NotBlank(message = "city request")
    private String city;

}
