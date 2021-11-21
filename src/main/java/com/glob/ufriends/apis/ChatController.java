package com.glob.ufriends.apis;

import com.glob.ufriends.entities.ChatMessage;
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
    public ChatMessage register(@Payload String user, SimpMessageHeaderAccessor headerAccessor) {
        headerAccessor.getSessionAttributes().put("username", "117540697");
        ChatMessage a = new ChatMessage();
        a.setContent("Prro");
        a.setSender("117540697");
        return a;
    }

    /* Send message web chat */
    @MessageMapping("/chat.send")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
        return chatMessage;
    }
}