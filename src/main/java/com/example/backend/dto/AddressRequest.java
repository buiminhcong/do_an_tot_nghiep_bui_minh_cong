package com.example.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressRequest {

    @Size(max = 11, min = 10, message = "Number phone from 10 to 11")
    private String phone;

    @Email(message = "Email request")
    private String email;

    private String district;

    @NotBlank(message = "City request")
    private String city;

    private String note;

    private int numberOfDistrict;

    @NotBlank(message = "Date request")
    private Date dayOfBirth;

    private String avatar;
}
