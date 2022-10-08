package com.example.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomRequest {

    @NotBlank(message = "number request")
    private String number;

    @Min(10)
    @Max(100)
    private int seatingCapacity;

    private String note;
}
