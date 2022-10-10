package com.example.backend.controller;

import com.example.backend.dto.RoomRequest;
import com.example.backend.entity.Room;
import com.example.backend.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/room")
@CrossOrigin("http://localhost:4200")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @GetMapping("")
    public List<Room> getAllRoom(){
        return roomService.getListRoom();
    }

    @PostMapping("/create")
    public ResponseEntity<Room> createRoom( @RequestBody @Valid RoomRequest roomDTO){
        return new ResponseEntity<>(roomService.createRoom(roomDTO), HttpStatus.CREATED);
    }

    @PostMapping("/update/{id}")
    public Room updateRoom(@PathVariable("id") int id,  @RequestBody RoomRequest room){
        return roomService.updateRoom(id, room);
    }

    @GetMapping("/{number}")
    public Room findRoomByNumber( @PathVariable("number") String number){
        return roomService.findRoomByNumber(number);
    }

    @GetMapping("/remove/{id}")
    public Boolean removeRoomById ( @PathVariable("id") int id){
        return roomService.removeRoomById(id);
    }


}
