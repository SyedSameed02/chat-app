package com.chat_app.chat_app_backend.repositories;

import com.chat_app.chat_app_backend.entites.Room;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends MongoRepository<Room,String> {

    Room findByRoomId(String roomId);
}
