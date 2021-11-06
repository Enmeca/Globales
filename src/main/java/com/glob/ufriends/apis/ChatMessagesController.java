package com.glob.ufriends.apis;

import com.glob.ufriends.entities.ChatMessages;
import com.glob.ufriends.services.ChatMessagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "/api/v1/chatMessages")
@RestController
public class ChatMessagesController {

    @Autowired
    private ChatMessagesService service;


    /*
     * JSON Object for Forum Comments (POST):
     *
     * {
     *   id: int, // Not needed in post. This is autogenerated via a sequence in the db
     *   chatUid: {id: int}, // Matched user object that has the id of the "chat"
     *   userUid: {id: String}, // User object that has the id of the user that sent the message
     *   message: String,
     *   dateSent: Date || null, // Not needed in post. The date is set in the DB using a trigger
     *   wasRead: int (boolean) // 0 for false, 1 for true
     * }
     *
     * */


    @PostMapping
    public ChatMessages addChatMessages(@RequestBody ChatMessages chatMessages) {
        return service.saveChatMessages(chatMessages);
    }

    @GetMapping(path = "{id}")
    public ChatMessages getChatMessage(@PathVariable("id") int id) {
        return service.getChatMessages(id);
    }
    
    // The parameter is the chatUID (The id of the Matched Users Object).
    // Order is from oldest to newest
    @GetMapping(path = "/chat/{id}")
    public List<ChatMessages> findChatMessagesByChatID(@PathVariable("id") int id) {
        return service.findChatMessagesByChatID(id);
    }

    // The parameter is the chatUID. Order is from newest to oldest
    @GetMapping(path = "/chat/orderByDateDesc/{id}")
    public List<ChatMessages> getChatMessagesByChatIDOrderedBySDate(@PathVariable("id") int id) {
        return service.getChatMessagesOrderedBySDate(id);
    }

    // The parameters are the chat (MatchedUsers) ID and the user Id. Returns a list with the messages
    // that a specific user has not read from a specific chat.
    @GetMapping(path = "/chat/orderByDateDesc/{chatId}/{userId}")
    public List<ChatMessages> getChatMessagesNotReadByUserByChat(@PathVariable("chatId") int chatId,
    @PathVariable("userId") String userId) {
        return service.getChatMessagesNotReadByUserByChat(chatId,userId);
    }

    @Transactional
    @PutMapping(path = "/readMessages/{chatId}/{userId}")
    public void readNotSeenMessagesByChatIdAndUserId(@PathVariable("chatId") int chatId,
    @PathVariable("userId") String userId){
        service.readNotSeenMessagesByChatIdAndUserId(chatId, userId);
    }

    // Be sure to send an object with the id
    @Transactional
    @DeleteMapping
    public void deleteForumComments(@RequestBody ChatMessages forumComments){

        service.deleteChatMessages(forumComments);
    }
    
}
