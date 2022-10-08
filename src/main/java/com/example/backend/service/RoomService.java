package com.example.backend.service;

import com.example.backend.dto.RoomRequest;
import com.example.backend.entity.Room;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoomService {

    List<Room> getListRoom();

    Room createRoom(RoomRequest roomDTO);

    Room updateRoom(int id, RoomRequest roomRequest);

    Room findRoomByNumber(String number);

    Boolean removeRoomById(int id);
}
