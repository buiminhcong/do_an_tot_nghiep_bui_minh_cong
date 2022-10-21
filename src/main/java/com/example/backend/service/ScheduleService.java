package com.example.backend.service;

import com.example.backend.dto.ScheduleDTO;
import com.example.backend.entity.Class;
import com.example.backend.entity.ga.Data;
import com.example.backend.entity.ga.Schedule;
import com.example.backend.entity.ga.ScheduleEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ScheduleService {

    ScheduleDTO gaSchedule();

    ScheduleEntity getScheduleById(int id);

    ScheduleEntity createSchedule(List<Class> dto);

    ScheduleEntity getSchedule();

}
