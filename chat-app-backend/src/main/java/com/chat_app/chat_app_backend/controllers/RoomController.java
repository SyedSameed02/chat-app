package com.chat_app.chat_app_backend.controllers;


import com.chat_app.chat_app_backend.entites.Message;
import com.chat_app.chat_app_backend.entites.Room;
import com.chat_app.chat_app_backend.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.annotation.Repeatable;
import java.util.List;

@RestController
@RequestMapping("/api/v1/rooms")
@CrossOrigin("http://localhost:5173")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @PostMapping
    public ResponseEntity<?> createRoom(@RequestBody  String roomId)
    {
        if(roomService.findByRoomId(roomId) != null)
        {
            return ResponseEntity.badRequest().body("Room already exists");
        }
        Room room =  new Room();
        room.setRoomId(roomId);
        roomService.save(room);
        return  ResponseEntity.status(HttpStatus.CREATED).body(room);
    }

    @GetMapping("/{roomId}")
    public  ResponseEntity<?> joinRoom(@PathVariable String roomId)
    {
        Room room = roomService.findByRoomId(roomId);

        if(room == null)
        {
            return ResponseEntity.badRequest().body("Room not found");
        }

        return ResponseEntity.ok(room);
    }


    //getMessegesFromRoom

    @GetMapping("/{roomId}/messages")
    public ResponseEntity<List<Message>> getMessages(
            @PathVariable String roomId,
            @RequestParam(value = "page",defaultValue = "0",required = false) int page,
            @RequestParam(value = "size",defaultValue = "20",required = false) int size
    ){
        Room room = roomService.findByRoomId(roomId);
        if(room == null)
        {
            return ResponseEntity.badRequest().build();
        }
        List<Message> messages = room .getMessages();

        int start = Math.max(0,messages.size()-(page+1)*size);
        int end = Math.min(messages.size(),start + size);
        List<Message>   paginatedMessages = messages.subList(start,end);
        return ResponseEntity.ok(paginatedMessages) ;

    }



}
