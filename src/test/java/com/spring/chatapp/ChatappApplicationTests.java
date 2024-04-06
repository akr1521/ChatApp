package com.spring.chatapp;

import com.spring.chatapp.controller.AppController;
import com.spring.chatapp.exception.ErrorConstants;
import com.spring.chatapp.exception.InvalidMessageException;
import com.spring.chatapp.model.ChatMessage;
import com.spring.chatapp.service.ChatAppService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class ChatappApplicationTests {


	@Test
	void contextLoads() {
	}



	@Autowired
	private AppController chatController;

	@MockBean
	private ChatAppService chatAppService; // Mock the ChatAppService

	@Test
	public void testSendMessage_validRequest_returnsCreated() throws Exception {

		// Create a valid ChatMessage request object
		ChatMessage request = new ChatMessage();
		request.setMessage("Hi There !!");
		request.setPostedBy("Adriana");

		// Mock ChatAppService behavior - simulate successful save
		Mockito.when(chatAppService.saveMessage(request)).thenReturn(request);

		// Perform the POST request with the message
		ResponseEntity<ChatMessage> response = chatController.sendMessage(request);

		// Assert response status and body
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertEquals(request, response.getBody());
	}

	@Test
	public void testDeleteMessage() {
		String messageId = "123";
		ResponseEntity<Void> response = chatController.deleteMessage(messageId);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		Mockito.verify(chatAppService).deleteMessageById(messageId);
	}
	@Test
	public void testDeleteLastNMessages() {
		int number = 2;
		ResponseEntity<Void> response = chatController.deleteLastNMessages(number);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		Mockito.verify(chatAppService).deleteLastNChats(number);
	}
	@Test
	public void testGetAllMessages() {
		List<ChatMessage> mockMessages = Arrays.asList(new ChatMessage(), new ChatMessage());
		Mockito.when(chatAppService.getAllChats()).thenReturn(mockMessages);
		ResponseEntity<Object> response = chatController.getAllMessages();
		assertEquals(HttpStatus.OK, response.getStatusCode());
		Mockito.verify(chatAppService , Mockito.times(2)).getAllChats();
	}



}
