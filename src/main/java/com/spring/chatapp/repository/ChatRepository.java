package com.spring.chatapp.repository;

import com.spring.chatapp.model.ChatMessage;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface ChatRepository extends MongoRepository<ChatMessage, String> {

   //     @Query(value="{'id' : $0}", delete = true)
   //     void deleteById(String id);


        @Query(value = "{ 'postedBy' : ?0 }", delete = true)
        void  deleteByPostedBy(String postedBy);


        @Query(value = "{ 'postedBy' : ?0 }")
        java.util.List<ChatMessage> findByPostedBy(String postedBy);
}
