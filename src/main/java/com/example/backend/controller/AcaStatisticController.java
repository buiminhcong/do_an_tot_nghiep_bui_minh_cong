package com.example.backend.controller;

import com.example.backend.dto.statistic.InstructorStatistic;
import com.example.backend.dto.statistic.MeetingStatistic;
import com.example.backend.dto.statistic.RoomStatic;
import com.example.backend.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/statistic")
@CrossOrigin("http://localhost:4200")
public class AcaStatisticController {

    @Autowired
    private ClassService classService;

    @GetMapping("/room")
    public List<RoomStatic> getRoomStatistic(){
        return classService.getRoomStatic();
    }

    @GetMapping("/meeting")
    public List<MeetingStatistic> getMeetingStatistic() {
        return classService.getMeetingStatistic();
    }

    @GetMapping("/instructor")
    public List<InstructorStatistic> getInstructorStatistic() {
        return classService.getInstructorStatistic();
    }

}
