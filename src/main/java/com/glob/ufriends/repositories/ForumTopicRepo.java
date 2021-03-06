package com.glob.ufriends.repositories;


import com.glob.ufriends.entities.ForumTopic;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ForumTopicRepo extends JpaRepository<ForumTopic, Integer> {
    
    @Query(value = "select * from FORUM_TOPICS ft order by ft.CREATION_DATE desc",
            nativeQuery = true)
    List<ForumTopic> getForumTopicsOrderedByCDate();
    
    @Modifying
    @Query(value = "update FORUM_TOPICS ft set ft.COMMENTS_QUANTITY = ?1 where ft.ID = ?2",
            nativeQuery = true)
    void updateForumTopicCommentsQuantity(int comments, int forumId);
    
}
