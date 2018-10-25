package com.techniki.www.chatApp.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Participant {
    @NotEmpty
    private String id;
    @NotEmpty
    private String name;

    public void generateUniqueId() {
        this.id = (new ObjectId()).toHexString();
    }

}
