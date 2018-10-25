package com.techniki.www.chatApp.models;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Data
public class Message {
    @Id
    private String id;
    private Date created;
    private String from;
    private String text;
    private String conversationID;
    private Boolean inChatRoom;

    public void generateUniqueId() {
        this.id = (new ObjectId()).toHexString();
    }

}
