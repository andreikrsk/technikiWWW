package com.techniki.www.chatApp.exceptions.handlers;

import com.techniki.www.chatApp.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class CustomResponseEntityExceptionHandler {

    @ExceptionHandler(UserAlreadyExistsException.class)
    public final ResponseEntity handleUserAlreadyExistsException(){
        return new ResponseEntity(HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UserDontExistException.class)
    public final ResponseEntity handleUserDontExistException(){return new ResponseEntity(HttpStatus.BAD_REQUEST);}

    @ExceptionHandler(BlogDoesntExistException.class)
    public final ResponseEntity handleBlogDoesntExistException(){
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoMessageFoundException.class)
    public final ResponseEntity handleNoMessageFoundException(){return new ResponseEntity(HttpStatus.NOT_FOUND);}

    @ExceptionHandler(ForbiddenActionOnBlog.class)
    public final ResponseEntity handleForbiddenActionOnBlog(){return new ResponseEntity(HttpStatus.FORBIDDEN);}
}

