package com.techniki.www.chatApp.services;

import com.techniki.www.chatApp.exceptions.UserAlreadyExistsException;
import com.techniki.www.chatApp.exceptions.UserDontExistException;
import com.techniki.www.chatApp.models.User;

import java.util.List;

public interface IUserService {
    List<User> getAll();
    User add(User user) throws UserAlreadyExistsException;
    User get(String name) throws UserDontExistException;
}
