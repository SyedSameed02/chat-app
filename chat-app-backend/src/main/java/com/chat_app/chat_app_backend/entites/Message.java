package com.chat_app.chat_app_backend.entites;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Message {

    private String sender;
    private String content;
    private LocalDateTime timeStamp;




}
