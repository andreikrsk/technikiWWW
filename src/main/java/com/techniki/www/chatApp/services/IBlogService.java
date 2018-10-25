package com.techniki.www.chatApp.services;

import com.techniki.www.chatApp.exceptions.BlogDoesntExistException;
import com.techniki.www.chatApp.models.Blog;

import java.util.List;

public interface IBlogService {
    List<Blog> getAll();
    Blog add(Blog blog);
    Blog getById(String id) throws BlogDoesntExistException;
    Blog change(Blog blog) throws BlogDoesntExistException;
    void delete(String id) throws BlogDoesntExistException;
}
