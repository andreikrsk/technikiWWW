package com.techniki.www.chatApp.models;

import lombok.Data;
import org.springframework.data.annotation.Id;


@Data
public class Comment {
    @Id
    private String blogId;
    private String comment;
    private String commentator;
}
