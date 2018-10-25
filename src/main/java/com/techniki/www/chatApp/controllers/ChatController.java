package com.techniki.www.chatApp.controllers;

import com.techniki.www.chatApp.exceptions.NoMessageFoundException;
import com.techniki.www.chatApp.exceptions.UserDontExistException;
import com.techniki.www.chatApp.models.Conversation;
import com.techniki.www.chatApp.services.IConversationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/messages")
public class ChatController {

    private IConversationService conversationService;

    @Autowired
    ChatController(IConversationService conversationService) {
        this.conversationService = conversationService;
    }

    @GetMapping("/")
    public ResponseEntity<Conversation> getChatroomConversation() throws NoMessageFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(conversationService.getChatRoom());
    }

    @GetMapping("/{name1}/{name2}")
    public ResponseEntity<Conversation> getConversationByUsersNames(
            @PathVariable(value = "name1") String name1,
            @PathVariable(value = "name2") String name2) throws UserDontExistException, NoMessageFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(conversationService.getByName(name1, name2));
    }
}
