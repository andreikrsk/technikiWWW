package com.techniki.www.chatApp.repositories;

import com.techniki.www.chatApp.models.Blog;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface BlogRepository extends MongoRepository<Blog, String> {

    List<Blog> findAll();

    Optional<Blog> findById(String id);
}
