package com.techniki.www.chatApp.exceptions;

public class UserDontExistException extends Exception {

    public UserDontExistException(String msg){
        super(msg);
    }
}
