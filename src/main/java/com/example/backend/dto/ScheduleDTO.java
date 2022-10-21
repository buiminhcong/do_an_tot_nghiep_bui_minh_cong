package com.example.backend.dto;

import com.example.backend.entity.Class;
import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleDTO {

    private List<Class> classes;

}
