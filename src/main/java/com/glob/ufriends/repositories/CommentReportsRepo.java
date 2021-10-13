package com.glob.ufriends.repositories;

import java.util.List;

import com.glob.ufriends.entities.CommentReports;
import com.glob.ufriends.entities.CommentReportsId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


public interface CommentReportsRepo extends JpaRepository<CommentReports, CommentReportsId>{
 
    @Query(value = "select * from COMMENT_REPORTS cr order by cr.CREATION_DATE desc",
            nativeQuery = true)
    List<CommentReports> getCommentReportsOrderedByCDate();

    @Modifying
    @Query(value = "delete from COMMENT_REPORTS cr where cr.COMMENT_ID = ?1 and cr.USER_UID = ?2",
            nativeQuery = true)
    void deleteCommentReportsById(int commentId,String userId);
    
    @Modifying
    @Query(value = "delete from COMMENT_REPORTS cr where cr.COMMENT_ID = ?1",
            nativeQuery = true)
    void deleteCommentReportsByCommentId(int commentId);

}
