package com.glob.ufriends.apis;

import com.glob.ufriends.entities.ChatMessages;
import com.glob.ufriends.services.ChatMessagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class ChatController {

    @Autowired
    private ChatMessagesService service;
    
    @MessageMapping("/chat.register")
    @SendTo("/topic/public")
    public ChatMessages register(@RequestBody ChatMessages chatMessages, SimpMessageHeaderAccessor headerAccessor) {
        headerAccessor.getSessionAttributes().put("username", chatMessages.getUserUid().getId());
        return chatMessages;
    }

    @MessageMapping("/chat.send")
    @SendTo("/topic/public")
    public ChatMessages sendMessage(@RequestBody ChatMessages chatMessage) {
        ChatMessages cm = service.saveChatMessages(chatMessage);
        return cm;
    }
}