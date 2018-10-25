package com.techniki.www.chatApp.repositories;

import com.techniki.www.chatApp.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    @Override
    List<User> findAll();

    Optional<User> findByUsername(String name);
}
