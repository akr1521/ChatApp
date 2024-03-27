package com.spring.chatapp.repository;

import com.spring.chatapp.model.Message;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MessageRepository extends MongoRepository <Message,String> {


}
