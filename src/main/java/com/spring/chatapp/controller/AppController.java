package com.spring.chatapp.controller;

import com.spring.chatapp.exception.ErrorConstants;
import com.spring.chatapp.exception.InvalidMessageException;
import com.spring.chatapp.model.ChatMessage;
import com.spring.chatapp.service.ChatAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
public class AppController {

@Autowired
private ChatAppService chatAppService;


    @RequestMapping (value="/message/send" , method= RequestMethod.POST , consumes = MediaType.ALL_VALUE)
    public ResponseEntity<ChatMessage>   sendMessage(@RequestBody ChatMessage request ) {
        if (Objects.isNull( request) ||  Objects.isNull( request.getPostedBy())){
             throw new InvalidMessageException(ErrorConstants.INVALID_REQUEST_ERROR_MESSAGE);
        }
        ChatMessage  persistedMessage =chatAppService.saveMessage(request);
        return new ResponseEntity<>(  persistedMessage, HttpStatus.CREATED);
    }


    @RequestMapping (value ="/message/all" ,method =  RequestMethod.GET ,produces = MediaType.APPLICATION_JSON_VALUE ,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getAllMessages ( ){
        return new ResponseEntity<>( chatAppService.getAllChats() , HttpStatus.OK
        );
    }

    @RequestMapping (value="/users/{username}" ,method =  RequestMethod.GET ,  produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object>  getAllUsers(@PathVariable("username") String username){
         List<ChatMessage> allChats=  chatAppService.findAllChatsByUserName( username);
        return new ResponseEntity<>(  allChats , HttpStatus.OK);
    }

    @RequestMapping (value= "/message/{id}" , method =  RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void>   deleteMessage(@PathVariable("id")    String id){

        chatAppService.deleteMessageById( id  );
        return new ResponseEntity<>( HttpStatus.OK);
    }

    @RequestMapping (value= "/message/last/{number}" , method =  RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void>   deleteLastNMessages(@PathVariable("number")   int number){

        chatAppService.deleteLastNChats( number  );
        return new ResponseEntity<>( HttpStatus.OK);
    }

}
