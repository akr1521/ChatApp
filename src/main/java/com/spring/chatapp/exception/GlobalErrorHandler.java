package com.spring.chatapp.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalErrorHandler {


    @ExceptionHandler(value = {ResourceNotFoundException.class})
    public ResponseEntity<ErrorApiResponse> resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        ErrorApiResponse message = new ErrorApiResponse( ex.getMessage() , ErrorConstants.USER_NOT_FOUND_ERROR_CODE,  System.currentTimeMillis());
        return new ResponseEntity<ErrorApiResponse>(message, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {InvalidMessageException.class})
    public ResponseEntity<ErrorApiResponse> InvalidMessageException(InvalidMessageException ex, WebRequest request) {
        ErrorApiResponse message = new ErrorApiResponse( ex.getMessage() , ErrorConstants.INVALID_REQUEST_ERROR_CODE,  System.currentTimeMillis());
        return new ResponseEntity<ErrorApiResponse>(message, HttpStatus.BAD_REQUEST);
    }
}
