package com.techniki.www.chatApp.controllers;

import com.techniki.www.chatApp.exceptions.BlogDoesntExistException;
import com.techniki.www.chatApp.exceptions.ForbiddenActionOnBlog;
import com.techniki.www.chatApp.exceptions.UserDontExistException;
import com.techniki.www.chatApp.models.Blog;
import com.techniki.www.chatApp.models.Comment;
import com.techniki.www.chatApp.models.User;
import com.techniki.www.chatApp.services.IBlogService;
import com.techniki.www.chatApp.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/blogs")
public class BlogController {
    private IBlogService blogService;

    private IUserService userService;

    @Autowired
    BlogController(IBlogService blogService, IUserService userService) {
        this.blogService = blogService;
        this.userService = userService;
    }

    @PostMapping("/newBlog")
    public ResponseEntity<Resource<String>> createNewBlog(@RequestBody Blog blog, HttpServletRequest httpServletRequest) {
        blog.setCreatedBy(httpServletRequest.getUserPrincipal().getName());
        Blog insertedBlog = blogService.add(blog);
        Resource<String> resource = new Resource<>(insertedBlog.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(resource);
    }

    @GetMapping("/allBlogs")
    public List<Blog> getAllBlogs() {
        return blogService.getAll();
    }

    @GetMapping("/singleBlog/{id}")
    public Blog getSingleBlog(@PathVariable(value="id") String id) throws BlogDoesntExistException {
        return blogService.getById(id);
    }

    @PutMapping("/updateBlog")
    public ResponseEntity<Blog> updateBlog(@RequestBody Blog blog) throws BlogDoesntExistException {
        Blog changedBlog = blogService.change(blog);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(changedBlog);
    }

    @DeleteMapping("/deleteBlog/{id}")
    public void deleteBlog(@PathVariable(value="id") String id) throws BlogDoesntExistException {
        blogService.delete(id);
    }

    @PutMapping("/likeBlog")
    public ResponseEntity<Blog> likeBlog(@RequestBody Blog blog, HttpServletRequest httpServletRequest) throws BlogDoesntExistException, UserDontExistException, ForbiddenActionOnBlog {
        System.out.println("httpServletRequest.getUserPrincipal() - > " + httpServletRequest.getUserPrincipal().getName());
        Blog likeBlog = blogService.getById(blog.getId());
        User likedUser = userService.get(httpServletRequest.getUserPrincipal().getName());
        if (likeBlog.getCreatedBy().equals(likedUser.getUsername())) {
            throw new ForbiddenActionOnBlog(String.format("U can't like your own blog with id: %s", likeBlog.getId()));
        }else if(!(likeBlog.getLikedBy() == null) && likeBlog.getLikedBy().contains(likedUser)){
            throw new ForbiddenActionOnBlog(String.format("U already liked this blog with id: %s", likeBlog.getId()));
        }else if(!(likeBlog.getLikedBy() == null) && likeBlog.getDislikedBy().contains(likedUser)){
            likeBlog.setDislikes(likeBlog.getDislikes() - 1);
            likeBlog.getDislikedBy().remove(likedUser);
            likeBlog.setLikes(likeBlog.getLikes() + 1);
            likeBlog.getLikedBy().add(likedUser);
        }else {
            likeBlog.setLikes(likeBlog.getLikes() + 1);
            likeBlog.getLikedBy().add(likedUser);
        }
        Blog changedBlog = blogService.change(likeBlog);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(changedBlog);
    }

    @PutMapping("/dislikeBlog")
    public ResponseEntity<Blog> dislikeBlog(@RequestBody Blog blog, HttpServletRequest httpServletRequest) throws BlogDoesntExistException, UserDontExistException, ForbiddenActionOnBlog{
        System.out.println("httpServletRequest.getUserPrincipal() - > " + httpServletRequest.getUserPrincipal().getName());
        Blog dislikeBlog = blogService.getById(blog.getId());
        User dislikedUser = userService.get(httpServletRequest.getUserPrincipal().getName());
        if (dislikeBlog.getCreatedBy().equals(dislikedUser.getUsername())) {
            throw new ForbiddenActionOnBlog(String.format("U can't dislike your own blog with id: %s", dislikeBlog.getId()));
        }else if(!(dislikeBlog.getLikedBy() == null) && dislikeBlog.getDislikedBy().contains(dislikedUser)){
            throw new ForbiddenActionOnBlog(String.format("U already disliked this blog with id: %s", dislikeBlog.getId()));
        }else if(!(dislikeBlog.getLikedBy() == null) && dislikeBlog.getLikedBy().contains(dislikedUser)){
            dislikeBlog.setLikes(dislikeBlog.getLikes() - 1);
            dislikeBlog.getLikedBy().remove(dislikedUser);
            dislikeBlog.setDislikes(dislikeBlog.getDislikes() + 1);
            dislikeBlog.getDislikedBy().add(dislikedUser);
        }else {
            dislikeBlog.setDislikes(dislikeBlog.getDislikes() + 1);
            dislikeBlog.getDislikedBy().add(dislikedUser);
        }
        Blog changedBlog = blogService.change(dislikeBlog);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(changedBlog);
    }
    //TODO check form of request for blog and comment data
    @PostMapping("/comment")
    public ResponseEntity<Blog> addComment(@RequestBody Comment comment, HttpServletRequest httpServletRequest) throws BlogDoesntExistException, UserDontExistException {
        Blog commentedBlog = blogService.getById(comment.getBlogId());
        Comment addedComent = comment;
        User commentator = userService.get(httpServletRequest.getUserPrincipal().getName());
        addedComent.setCommentator(commentator.getUsername());
        commentedBlog.getComments().add(addedComent);
        Blog changedBlog = blogService.change(commentedBlog);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(changedBlog);
    }
}
