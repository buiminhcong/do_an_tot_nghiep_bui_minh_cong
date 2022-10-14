package com.example.backend.dto;

import com.example.backend.entity.Subject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModuleRequest {

    @NotBlank(message = "Name request")
    private String name;

    @NotBlank(message = "Code request")
    private String moduleCode;

    @Min(1)
    @Max(10)
    private int credits;

    @Min(1)
    @Max(1000)
    private int id_subject;

}
