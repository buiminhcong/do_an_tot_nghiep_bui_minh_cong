package com.example.backend.controller;

import com.example.backend.entity.MeetingTime;
import com.example.backend.entity.Room;
import com.example.backend.service.MeetingTimeService;
import com.example.backend.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/meeting-time")
@CrossOrigin("http://localhost:4200")
public class MeetingTimeController {

    @Autowired
    private MeetingTimeService meetingTimeService;

    @GetMapping("")
    public List<MeetingTime> getAllRoom(){
        return meetingTimeService.getListMeetingTime();
    }

}
