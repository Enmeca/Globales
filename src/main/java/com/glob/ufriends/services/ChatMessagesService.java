package com.glob.ufriends.services;

import com.glob.ufriends.entities.ChatMessages;
import com.glob.ufriends.repositories.ChatMessagesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatMessagesService {
    @Autowired
    private ChatMessagesRepo repository;

    public ChatMessages saveChatMessages(ChatMessages obj){
        return repository.save(obj);
    }

    public ChatMessages getChatMessage(int id){
        return repository.getById(id);
    }
    
    public List<ChatMessages> getAllChatMessages(){
        return repository.findAll();
    }

    public List<ChatMessages> findChatMessagesByChatID(int chatId){
        return repository.findChatMessagesByChatID(chatId);
    }
    
    public List<ChatMessages> getChatMessagesOrderedBySDate(int chatId){
        return repository.getChatMessagesByChatIdOrderedBySDate(chatId);
    }

    public ChatMessages getChatMessages(int id){
        return repository.findById(id).orElse(null);
    }

    public List<ChatMessages> getChatMessagesNotReadByUserByChat(int chatId, String userId){
        return repository.getChatMessagesNotReadByUserByChat(chatId,userId);
    }

    public void readNotSeenMessagesByChatIdAndUserId(int chatId, String userId){
        repository.readNotSeenMessagesByChatIdAndUserId(chatId, userId);
    }

    public void deleteChatMessages(ChatMessages obj){
        repository.delete(obj);
    }

}
