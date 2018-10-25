package com.techniki.www.chatApp.services;

import com.techniki.www.chatApp.exceptions.NoMessageFoundException;
import com.techniki.www.chatApp.models.Message;

import java.util.List;

public interface IMessageService {
    Message add(Message message);
    List<Message> get();
    Message addByConv(String id) throws NoMessageFoundException;
}
