package com.techniki.www.chatApp.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;


import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Data
//@Builder
//@JsonDeserialize(builder = User.UserBuilder.class)
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    private String id;
    @NotEmpty
    @Min(3)
    @Max(10)
    private String username;
    @NotEmpty
    @Min(8)
    @Max(50)
    private String password;

    public void generateUniqueId() {
        this.id = (new ObjectId()).toHexString();
    }
}
