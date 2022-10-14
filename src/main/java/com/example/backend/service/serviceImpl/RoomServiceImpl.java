package com.example.backend.service.serviceImpl;

import com.example.backend.dto.RoomRequest;
import com.example.backend.entity.Room;
import com.example.backend.exception.AlreadyExistException;
import com.example.backend.exception.NotFoundException;
import com.example.backend.repository.RoomRepository;
import com.example.backend.service.RoomService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public List<Room> getListRoom() {
        List<Room> rooms = new ArrayList<>();
        rooms = roomRepository.findAllRooms();
        return rooms;
    }

    @Override
    public Room createRoom(RoomRequest roomDTO) throws NotFoundException {
        Room r = roomRepository.findRoomByNumber(roomDTO.getNumber());
        if (r == null) {
            Room room = mapper.map(roomDTO, Room.class);
            room.setDeleted(0);
            return roomRepository.save(room);
        } else {
            throw new NotFoundException("Room " + roomDTO.getNumber() + " is already!");
        }
    }

    @Override
    public Room updateRoom(int id, RoomRequest roomRequest) throws NotFoundException{
        Optional<Room> optionalRoom = roomRepository.findById(id);
        Optional<Room> r = Optional.ofNullable(roomRepository.findRoomByNumber(roomRequest.getNumber()));
        if (optionalRoom.isPresent()) {
            if(!r.isPresent()){
                Room r1 = optionalRoom.get();
                r1.setNote(roomRequest.getNote());
                r1.setNumber(roomRequest.getNumber());
                r1.setSeatingCapacity(roomRequest.getSeatingCapacity());
                roomRepository.save(r1);
                return r1;
            }else {
                Room r1 = r.get();
                if(r1.getNumber().equals(roomRequest.getNumber())){
                    r1.setNote(roomRequest.getNote());
                    r1.setNumber(roomRequest.getNumber());
                    r1.setSeatingCapacity(roomRequest.getSeatingCapacity());
                    roomRepository.save(r1);
                    return r1;
                }else {
                    throw new NotFoundException("room name: "+ roomRequest.getNumber() + " exits");
                }
            }

        }
        throw new NotFoundException("room id: "+ id + " not found");
    }

    @Override
    public Room findRoomByNumber(String number) throws NotFoundException {
        Room room = roomRepository.findRoomByNumber(number);
        if (room != null) {
            return room;
        }
        throw new NotFoundException("Room not found with room number : " + number);

    }

    @Override
    public Boolean removeRoomById(int id) throws NotFoundException {
        Optional<Room> optionalRoom = roomRepository.findById(id);
        if(optionalRoom.isPresent() && optionalRoom.get().getDeleted()==0){
            Room room = optionalRoom.get();
            room.setDeleted(1);
            roomRepository.save(room);
            return true;
        }
        throw new NotFoundException("Not found Room with id: " + id);
    }
}
