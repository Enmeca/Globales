package com.glob.ufriends.repositories;

import com.glob.ufriends.entities.ChatMessages;
import java.util.Date;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChatMessagesRepo extends JpaRepository<ChatMessages, Integer> {
    
    @Query(value = "select * from CHAT_MESSAGES cm where cm.CHAT_UID = ?1",
            nativeQuery = true)
    List<ChatMessages> findChatMessagesByChatID(int chatId);
    
    @Query(value = "select * from CHAT_MESSAGES cm where cm.CHAT_UID = ?1 order by cm.DATE_SENT desc",
            nativeQuery = true)
    List<ChatMessages> getChatMessagesByChatIdOrderedBySDate(int chatId);

    @Query(value = "select * from CHAT_MESSAGES cm where cm.CHAT_UID = ?1 and" +
            " cm.USER_UID <> ?2 and cm.WAS_READ = 0",
            nativeQuery = true)
    List<ChatMessages> getChatMessagesNotReadByUserByChat(int chatId, String userId);
    
    @Query(value = "select SYSDATE from dual", nativeQuery = true)
    public Date getCurrentDateTime();

    @Modifying
    @Query(value = "update CHAT_MESSAGES cm set cm.WAS_READ = 1 where cm.CHAT_UID = ?1 and" +
            " cm.USER_UID <> ?2 and cm.WAS_READ = 0",
            nativeQuery = true)
    void readNotSeenMessagesByChatIdAndUserId(int chatId, String userId);
}
