package com.chat_app.chat_app_backend.controllers;


import com.chat_app.chat_app_backend.entites.Message;
import com.chat_app.chat_app_backend.entites.Room;
import com.chat_app.chat_app_backend.payload.MessageRequest;
import com.chat_app.chat_app_backend.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@Controller
@CrossOrigin("http://localhost:5173")
public class ChatController {

    @Autowired
    private RoomService roomService;

    @MessageMapping("/sendMessage/{roomId}")
    @SendTo("/topic/room/{roomId}")
    public Message sendMessage(
            @DestinationVariable String roomId,
            @RequestBody MessageRequest request
    )
    {
            Room room = roomService.findByRoomId(roomId);

            Message message = new Message();
            message.setContent(request.getContent());
            message.setSender(request.getSender());
            message.setTimeStamp(LocalDateTime.now());

            if(room != null)
            {
                room.getMessages();
                roomService.save(room);
            }else {
                throw  new RuntimeException("room not found");
            }
            return message;

    }


}
