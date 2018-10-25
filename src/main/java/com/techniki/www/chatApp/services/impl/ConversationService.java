package com.techniki.www.chatApp.services.impl;

import com.techniki.www.chatApp.exceptions.NoMessageFoundException;
import com.techniki.www.chatApp.exceptions.UserDontExistException;
import com.techniki.www.chatApp.models.Conversation;
import com.techniki.www.chatApp.models.Message;
import com.techniki.www.chatApp.models.Participant;
import com.techniki.www.chatApp.models.User;
import com.techniki.www.chatApp.repositories.ConversationRepository;
import com.techniki.www.chatApp.repositories.MessageRepository;
import com.techniki.www.chatApp.repositories.UserRepository;
import com.techniki.www.chatApp.services.IConversationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ConversationService implements IConversationService {

    private ConversationRepository conversationRepository;

    private UserRepository userRepository;

    private MessageRepository messageRepository;

    @Autowired
    public ConversationService(ConversationRepository conversationRepository, UserRepository userRepository, MessageRepository messageRepository) {
        this.conversationRepository = conversationRepository;
        this.userRepository = userRepository;
        this.messageRepository = messageRepository;
    }

    public List<Conversation> get() {
        return conversationRepository.findAll();
    }

    public Conversation add(Conversation conversation) {
        conversation.generateUniqueId();
        return conversationRepository.insert(conversation);
    }

    public Conversation getByName(String participant1, String participant2) throws UserDontExistException, NoMessageFoundException {
        final String name1 = participant1 + participant2;
        final String name2 = participant2 + participant1;

        Conversation conversation;
        Participant newParticipant1;
        Participant newParticipant2;
        Optional<Conversation> byName = tryToGetConvByName(name1, name2);

        List<Participant> participants = new ArrayList<>();

        if (byName.isPresent()) {
            Optional<Message> byConvID = messageRepository.findByConversationID(byName.get().getId());
            if (byConvID.isPresent()) {
                return new Conversation(byName.get().getId(),
                        byName.get().getName(),
                        byName.get().getParticipants(),
                        (List<Message>) (byConvID.get())
                );
            } else {
                throw new NoMessageFoundException(String.format("Error retrieving messages for conversation: %s", byName.get().getName()));
            }
        } else {
            Optional<User> user1 = userRepository.findByUsername(participant1);
            Optional<User> user2 = userRepository.findByUsername(participant2);
            if (user1.isPresent() && user2.isPresent()) {

                newParticipant1 = new Participant(user1.get().getId(), user1.get().getUsername());
                newParticipant2 = new Participant(user2.get().getId(), user2.get().getUsername());

                participants.add(newParticipant1);
                participants.add(newParticipant2);

                conversation = new Conversation();
                conversation.generateUniqueId();
                conversation.setName(name1);
                conversation.setParticipants(participants);
                return conversationRepository.insert(conversation);
            } else {
                throw new UserDontExistException(String.format("Can't find users %s and %s", participant1, participant2));
            }
        }
    }

    public Conversation getChatRoom() throws NoMessageFoundException{
        final String name = "chat-room";
        Optional<Conversation> byName = conversationRepository.findByName(name);
        if(byName.isPresent()){
            Optional<Message> byConvID = messageRepository.findByConversationID(byName.get().getId());
            if(byConvID.isPresent()){
                return new Conversation(byName.get().getId(),
                        name,
                        byName.get().getParticipants(),
                        (List<Message>) (byConvID.get())
                );
            }
            throw new NoMessageFoundException(String.format("No message foud, when retrieving chatRoom messages"));
        }else {
            Conversation chatRoom = new Conversation();
            chatRoom.setName(name);
            chatRoom.generateUniqueId();
            return conversationRepository.insert(chatRoom);
        }
    }

    private Optional<Conversation> tryToGetConvByName(String name1, String name2) {
        Optional<Conversation> byName = conversationRepository.findByName(name1);
        if (byName.isPresent()) {
            return byName;
        } else {
            byName = conversationRepository.findByName(name2);
        }
        return byName;
    }
}
