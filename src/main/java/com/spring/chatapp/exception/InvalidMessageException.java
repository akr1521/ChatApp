package com.spring.chatapp.exception;

public class InvalidMessageException extends  RuntimeException{
private String message;
public InvalidMessageException(String  message){
    super(  message);
}

}
