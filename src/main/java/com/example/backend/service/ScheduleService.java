package com.example.backend.service;

import com.example.backend.entity.ga.Data;
import com.example.backend.entity.ga.Schedule;
import org.springframework.stereotype.Service;

@Service
public interface ScheduleService {

    Schedule gaSchedule();

}
