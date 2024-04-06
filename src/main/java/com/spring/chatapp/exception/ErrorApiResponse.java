package com.spring.chatapp.exception;

public class ErrorApiResponse {
    private String message;
    private int errorCode;

    private long timestamp;

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public ErrorApiResponse(String message, int errorCode , long timestamp) {
        this.message = message;
        this.errorCode = errorCode;
        this.timestamp =  timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
}
