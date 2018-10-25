package com.techniki.www.chatApp.repositories;

import com.techniki.www.chatApp.models.Conversation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ConversationRepository extends MongoRepository<Conversation, String> {

    List<Conversation> findAll();

    Optional<Conversation> findByName(String name);
}
