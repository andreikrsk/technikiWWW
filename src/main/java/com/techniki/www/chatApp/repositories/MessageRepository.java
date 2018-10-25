package com.techniki.www.chatApp.repositories;

import com.techniki.www.chatApp.models.Message;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface MessageRepository extends MongoRepository<Message, String> {
    List<Message> findAll();

    Optional<Message> findByConversationID(String id);
}
