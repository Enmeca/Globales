package com.glob.ufriends.repositories;

import com.glob.ufriends.entities.ForumComments;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ForumCommentsRepo extends JpaRepository<ForumComments, Integer> {
    
    @Query(value = "select * from FORUM_COMMENTS fc where fc.FORUM_ID = ?1",
            nativeQuery = true)
    List<ForumComments> findForumCommentsByForumID(int forumId);
    
    @Query(value = "select * from FORUM_COMMENTS fc where fc.FORUM_ID = ?1 order by fc.CREATION_DATE desc",
            nativeQuery = true)
    List<ForumComments> getForumCommentsOrderedByCDate(int forumId);
    
}
