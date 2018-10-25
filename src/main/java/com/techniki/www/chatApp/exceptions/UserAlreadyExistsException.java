package com.techniki.www.chatApp.exceptions;

public class UserAlreadyExistsException extends Exception {

    public UserAlreadyExistsException(String msg) {
        super(msg);
    }
}
