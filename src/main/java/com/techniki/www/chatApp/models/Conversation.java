package com.techniki.www.chatApp.models;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Conversation {
    @Id
    private String id;
    @NotEmpty
    private String name;
    @Singular
    private List<Participant> participants = new ArrayList<>();
    @Singular
    private List<Message> messages = new ArrayList<>();

    public void generateUniqueId() {
        this.id = (new ObjectId()).toHexString();
    }
}
