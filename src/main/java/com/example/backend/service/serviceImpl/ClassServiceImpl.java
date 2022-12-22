package com.example.backend.service.serviceImpl;

import com.example.backend.dto.ClassRequest;
import com.example.backend.dto.statistic.InstructorStatistic;
import com.example.backend.dto.statistic.MeetingStatistic;
import com.example.backend.dto.statistic.RoomStatic;
import com.example.backend.entity.Class;
import com.example.backend.repository.ClassRepository;
import com.example.backend.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ClassServiceImpl implements ClassService {

    @Autowired
    private ClassRepository classRepository;

    @Override
    public Class createClass(ClassRequest request) {

        Class c = new Class(request.getDept(), request.getCourse(), request.getInstructor()
                , request.getMeetingTime(), request.getRoom(), request.getSchedule());
        c.setDeleted(0);
        return classRepository.save(c);
    }

    @Override
    public List<Class> getScheduleByInstructor(int id) {
        List<Class> list = classRepository.getScheduleByInstructor(id);
        return list;
    }


    @Override
    public List<RoomStatic> getRoomStatic() {
        return classRepository.findRoomStatic();
    }

    @Override
    public List<MeetingStatistic> getMeetingStatistic() {
        return classRepository.findMeetingTime();
    }

    @Override
    public List<InstructorStatistic> getInstructorStatistic() {
        return classRepository.findInstructorStatistic();
    }
}
