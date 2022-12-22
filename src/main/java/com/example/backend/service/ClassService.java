package com.example.backend.service;

import com.example.backend.dto.ClassRequest;
import com.example.backend.dto.statistic.InstructorStatistic;
import com.example.backend.dto.statistic.MeetingStatistic;
import com.example.backend.dto.statistic.RoomStatic;
import com.example.backend.entity.Class;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ClassService {

    Class createClass(ClassRequest request);

    List<Class> getScheduleByInstructor(int id);

    List<RoomStatic> getRoomStatic();

    List<MeetingStatistic> getMeetingStatistic();

    List<InstructorStatistic> getInstructorStatistic();

//    Class getClassByIdCourse(int id);

}
