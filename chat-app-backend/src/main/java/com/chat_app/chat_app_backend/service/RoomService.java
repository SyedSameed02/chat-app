package com.chat_app.chat_app_backend.service;


import com.chat_app.chat_app_backend.entites.Room;
import com.chat_app.chat_app_backend.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;


    public Room findByRoomId(String roomId)
    {
        return  roomRepository.findByRoomId(roomId);
    }

    public void save(Room room)
    {
        roomRepository.save(room);
    }


}
