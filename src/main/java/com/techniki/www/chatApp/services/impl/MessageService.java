package com.techniki.www.chatApp.services.impl;

import com.techniki.www.chatApp.exceptions.NoMessageFoundException;
import com.techniki.www.chatApp.models.Message;
import com.techniki.www.chatApp.repositories.MessageRepository;
import com.techniki.www.chatApp.services.IMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class MessageService implements IMessageService {

    private MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public Message add(@NotNull Message message) {
        message.setCreated(new Date());
        return messageRepository.insert(message);
    }

    public List<Message> get() {
        return messageRepository.findAll();
    }

    public Message addByConv(String id) throws NoMessageFoundException {
        Optional<Message> byConv = messageRepository.findByConversationID(id);
        if (byConv.isPresent()) {
            return byConv.get();
        }
        throw new NoMessageFoundException(String.format("No message found by conversation id: %s", id));
    }
}
