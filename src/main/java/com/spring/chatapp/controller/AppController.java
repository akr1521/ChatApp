package com.spring.chatapp.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class AppController {





    @MessageMapping("chat/{roomName}")
    public ResponseEntity<Object>  sendMessage(@DestinationVariable String roomName,
                                               @Payload String message){

            return null ;
    }


}
