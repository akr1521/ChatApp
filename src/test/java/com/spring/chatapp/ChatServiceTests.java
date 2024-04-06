package com.spring.chatapp;


import com.spring.chatapp.model.ChatMessage;
import com.spring.chatapp.repository.ChatRepository;
import com.spring.chatapp.service.ChatAppService;
import com.spring.chatapp.utils.AppUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

public class ChatServiceTests {

    @InjectMocks
    private ChatAppService chatAppService;

    @Mock
    private ChatRepository chatRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllChats_callsRepositoryFindAll() {
        List<ChatMessage> expectedMessages = Arrays.asList(new ChatMessage(), new ChatMessage());
        when(chatRepository.findAll()).thenReturn(expectedMessages);
        List<ChatMessage> actualMessages = chatAppService.getAllChats();
        Mockito.verify(chatRepository).findAll();
        assertEquals(expectedMessages, actualMessages);
    }

    @Test
    public void testFindAllChatsByUserName_callsRepositoryFindByPostedBy() {
        String username = "akash";
        List<ChatMessage> expectedMessages = Arrays.asList(new ChatMessage());
        when(chatRepository.findByPostedBy(username)).thenReturn(expectedMessages);
        List<ChatMessage> actualMessages = chatAppService.findAllChatsByUserName(username);
        Mockito.verify(chatRepository , times(1)).findByPostedBy(username);
        assertEquals(expectedMessages, actualMessages);
    }

    @Test
    public void testSaveMessage_setsPostedAtAndCallsRepositorySave() {
        ChatMessage message = new ChatMessage();
        message.setPostedBy("Akash");

        message.setMessage("I am travelling");
        long currentTime = System.currentTimeMillis(); // Simulate current time
        message.setPostedAt(String.valueOf(currentTime));
        when( chatRepository.save(message)).thenReturn( message);

        try (MockedStatic<AppUtils> utilities = Mockito.mockStatic(AppUtils.class)) {
            utilities.when(AppUtils::getTime).thenReturn(String.valueOf(currentTime));

        }

        ChatMessage savedMessage = chatAppService.saveMessage(message);


        assertEquals(AppUtils.getTime(), savedMessage.getPostedAt());
        Mockito.verify(chatRepository , times(1)).save(message);
    }

    @Test
    public void testDeleteMessageByUsername_callsRepositoryDeleteByPostedBy() {
        String id = "66105317300c191178301693";
        Mockito.doNothing().when( chatRepository).deleteById(id);
        chatAppService.deleteMessageById(id);
        Mockito.verify(chatRepository).deleteById(id);
    }
}
