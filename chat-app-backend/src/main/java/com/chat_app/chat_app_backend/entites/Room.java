package com.chat_app.chat_app_backend.entites;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;


@Document(collection = "rooms")

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Room {

    @Id
    private  String id;//for DB

    private  String roomId;

    private List<Message> messages =  new ArrayList<>();


    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }
}
