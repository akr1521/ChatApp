package com.spring.chatapp.service;

import com.mongodb.client.result.DeleteResult;
import com.spring.chatapp.exception.ResourceNotFoundException;
import com.spring.chatapp.model.ChatMessage;
import com.spring.chatapp.repository.ChatRepository;
import com.spring.chatapp.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;


@Service
public class ChatAppService {

    @Autowired
    private MongoTemplate mongoTemplate;

    private final ChatRepository chatRepository ;
    public ChatAppService (  ChatRepository chatRepository){
        this.chatRepository = chatRepository;
    }

    public List<ChatMessage> getAllChats() {
        return chatRepository.findAll();
    }

    public List<ChatMessage> findAllChatsByUserName( String username) {
        List<ChatMessage>  chatMessages=    chatRepository.findByPostedBy(username);
        if(Objects.isNull(chatMessages)  || chatMessages.size()== 0){
            throw new ResourceNotFoundException("No user available!");
        }
        return  chatMessages;
    }

    /*
    public List<ChatMessage> getAllUsers( String username) {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.project("postedBy")
        );

        List<AggregationResults<BasicDBObject>> results = mongoTemplate.aggregate(aggregation, "chatMessage", BasicDBObject.class);
        Set<String> postedByValues = new HashSet<>();
        for (AggregationResults<BasicDBObject> result : results) {
            postedByValues.add(result.getMappedResults().get(0).getString("postedBy"));
        }
    }

     */



    //TBD before sleep
    public  ChatMessage saveMessage(ChatMessage message){
        message.setPostedAt(AppUtils.getTime());
        ChatMessage   savedChatMessage=  chatRepository.save(message );
        return savedChatMessage;
    }
    public void deleteMessageById( String id){

        chatRepository.deleteById(id);

    }

    public void deleteLastNChats( int n ) {
        Query query = new Query().with(Sort.by(Sort.Direction.DESC, "postedAt")).limit(n);
        DeleteResult result = mongoTemplate.remove(query,ChatMessage.class);

    }


}
