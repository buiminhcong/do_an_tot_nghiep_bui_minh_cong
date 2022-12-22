package com.example.backend.dto.statistic;

import com.example.backend.entity.Instructor;
import com.example.backend.entity.MeetingTime;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InstructorStatistic {

    private Long count;

    private Instructor instructor;

}
