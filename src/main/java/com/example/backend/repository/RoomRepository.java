package com.example.backend.repository;

import com.example.backend.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {

    @Query(value = "select * from room where deleted = 0", nativeQuery = true)
    List<Room> findAllRooms();

    @Query(value = "select * from room where number = ? and deleted = 0", nativeQuery = true)
    Room findRoomByNumber(String number);

}
