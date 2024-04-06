package com.spring.chatapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document(collection = "chatMessage")
    public class ChatMessage {
    @Id
    private String id; // Unique identifier for the message (ObjectId from MongoDB)
    @JsonProperty("message")
    private String message; // Text content of the message

    @JsonProperty("postedBy")
    private String postedBy;

    @JsonProperty("postedAt")
    private  String postedAt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPostedBy() {
        return postedBy;
    }

    public void setPostedBy(String postedBy) {
        this.postedBy = postedBy;
    }

    public String getPostedAt() {
        return postedAt;
    }

    public void setPostedAt(String postedAt) {
        this.postedAt = postedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChatMessage that = (ChatMessage) o;
        return Objects.equals(id, that.id) && Objects.equals(postedBy, that.postedBy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, postedBy);
    }

    @Override
    public String toString() {
        return "ChatMessage{" +
                "id='" + id + '\'' +
                ", message='" + message + '\'' +
                ", userName='" + postedBy + '\'' +
                '}';
    }
}
