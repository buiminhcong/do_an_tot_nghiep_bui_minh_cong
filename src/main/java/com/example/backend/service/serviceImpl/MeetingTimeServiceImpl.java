package com.example.backend.service.serviceImpl;

import com.example.backend.entity.MeetingTime;
import com.example.backend.repository.MeetingTimeRepository;
import com.example.backend.service.MeetingTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MeetingTimeServiceImpl implements MeetingTimeService {

    @Autowired
    MeetingTimeRepository meetingTimeRepository;

    @Override
    public List<MeetingTime> getListMeetingTime() {
        return meetingTimeRepository.findAllMeetingTime();
    }
}
