package com.techniki.www.chatApp.services.impl;

import com.techniki.www.chatApp.exceptions.BlogDoesntExistException;
import com.techniki.www.chatApp.models.Blog;
import com.techniki.www.chatApp.repositories.BlogRepository;
import com.techniki.www.chatApp.services.IBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BlogService implements IBlogService{

    private BlogRepository blogRepository;

    @Autowired
    BlogService(BlogRepository blogRepository){
        this.blogRepository = blogRepository;
    }

    public List<Blog> getAll(){
        return blogRepository.findAll();
    }

    public Blog add(Blog blog){
        blog.generateUniqueId();
        blog.setCreatedAt(new Date());
        return blogRepository.insert(blog);
    }

    public Blog getById(@NotNull String id) throws BlogDoesntExistException{
        final Optional<Blog> byId = blogRepository.findById(id);
        if(byId.isPresent()){
            return byId.get();
        }
        throw new BlogDoesntExistException(String.format("Blog with %s id doesn't exist", id));
    }

    public Blog change(@NotNull Blog blog) throws BlogDoesntExistException{
        final boolean blogExists = blogRepository.existsById(blog.getId());
        if(blogExists){
            return blogRepository.save(blog);
        }
        throw new BlogDoesntExistException(String.format("Blog with %s id doesn't exist", blog.getId()));
    }

    public void delete(@NotNull String id) throws BlogDoesntExistException{
        final boolean blogExists = blogRepository.existsById(id);
        if(blogExists){
            blogRepository.deleteById(id);
        }else {
            throw new BlogDoesntExistException(String.format("Blog with %s id doesn't exist", id));
        }
    }
}
