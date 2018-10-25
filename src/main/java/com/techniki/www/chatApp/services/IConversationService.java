package com.techniki.www.chatApp.services;

import com.techniki.www.chatApp.exceptions.NoMessageFoundException;
import com.techniki.www.chatApp.exceptions.UserDontExistException;
import com.techniki.www.chatApp.models.Conversation;

import java.util.List;

public interface IConversationService {
    List<Conversation> get();
    Conversation add(Conversation conversation);
    Conversation getByName(String participant1, String participant2) throws UserDontExistException, NoMessageFoundException;
    Conversation getChatRoom() throws NoMessageFoundException;
}
