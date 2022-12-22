package com.example.backend.repository;

import com.example.backend.dto.statistic.InstructorStatistic;
import com.example.backend.dto.statistic.MeetingStatistic;
import com.example.backend.dto.statistic.RoomStatic;
import com.example.backend.entity.Class;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassRepository extends JpaRepository<Class, Integer> {

    @Query(value ="select * from class where deleted = 0 and instructor_id = ?", nativeQuery = true)
    List<Class> getScheduleByInstructor(int id);

        @Query(value = "select new com.example.backend.dto.statistic.RoomStatic(count(s.id), s.room)" +
            " from Class s where s.deleted=0 group by s.room order by count (s.id) desc")
    List<RoomStatic> findRoomStatic();

    @Query(value = "select new com.example.backend.dto.statistic.MeetingStatistic(count(s.id), s.meetingTime)" +
            " from Class s where s.deleted=0 group by s.meetingTime order by count (s.id) desc")
    List<MeetingStatistic> findMeetingTime();

    @Query(value = "select new com.example.backend.dto.statistic.InstructorStatistic(count(s.id), s.instructor)" +
            " from Class s where s.deleted=0 group by s.instructor order by count (s.id) desc")
    List<InstructorStatistic> findInstructorStatistic();

    @Query(value ="select * from class where deleted = 0 and id = ?", nativeQuery = true)
    Class getClassByIdCourse(int id);

    @Query(value ="select * from class where deleted = 0 and module_id = ?", nativeQuery = true)
    List<Class> getScheduleByModuleId(int module_id);

}
