package com.example.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NameRequest {

    @NotBlank(message = "First name request")
    private String firstName;

    @NotBlank(message = "Last name request")
    private String lastName;

    private String middleName;

}
