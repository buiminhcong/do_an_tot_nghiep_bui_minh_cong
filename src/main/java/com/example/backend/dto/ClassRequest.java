package com.example.backend.dto;

import com.example.backend.entity.*;
import com.example.backend.entity.ga.ScheduleEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClassRequest {

    private Department dept;

    private Course course;

    private Instructor instructor;

    private MeetingTime meetingTime;

    private Room room;

    private ScheduleEntity schedule;
}
