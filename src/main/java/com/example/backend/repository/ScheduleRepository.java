package com.example.backend.repository;

import com.example.backend.entity.ga.ScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRepository extends JpaRepository<ScheduleEntity, Integer> {

    @Query(value = "select * from schedule_entity where deleted = 0", nativeQuery = true)
    ScheduleEntity findSchedule();

//    @Query(value = "select * from schedule_entity where id = ?;", nativeQuery = true)
//    ScheduleEntity findScheduleById(int id);




}
