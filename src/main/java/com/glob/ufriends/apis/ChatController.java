package com.glob.ufriends.apis;

import com.glob.ufriends.entities.ChatMessages;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    /* Register to web chat (user inline) */
    @MessageMapping("/chat.register")
    @SendTo("/topic/public")
    public ChatMessages register(@Payload ChatMessages chatMessages, SimpMessageHeaderAccessor headerAccessor) {
        headerAccessor.getSessionAttributes().put("username", chatMessages.getChatUid());
        return chatMessages;
    }

    /* Send message web chat */
    @MessageMapping("/chat.send")
    @SendTo("/topic/public")
    public ChatMessages sendMessage(@Payload ChatMessages chatMessage) {
        return chatMessage;
    }
}