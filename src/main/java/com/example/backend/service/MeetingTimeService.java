package com.example.backend.service;

import com.example.backend.entity.MeetingTime;
import com.example.backend.entity.Room;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MeetingTimeService {

    List<MeetingTime> getListMeetingTime();

}
