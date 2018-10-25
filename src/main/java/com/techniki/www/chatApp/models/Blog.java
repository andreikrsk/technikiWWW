package com.techniki.www.chatApp.models;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//import javax.persistence.Id;

@Data
public class Blog {
    @Id
    private String id;
    @NotEmpty
    @Min(3)
    @Max(15)
    private String title;
    @NotEmpty
    private String body;
    @NotEmpty
    private String createdBy;
    private Date createdAt;
    private int likes;
    private List<User> likedBy = new ArrayList<>();
    private int dislikes;
    private List<User> dislikedBy = new ArrayList<>();
    private List<Comment> comments = new ArrayList<>();

    public void generateUniqueId() {
        this.id = (new ObjectId()).toHexString();
    }
}
