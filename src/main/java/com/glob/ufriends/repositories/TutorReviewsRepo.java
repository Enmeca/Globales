package com.glob.ufriends.repositories;

import com.glob.ufriends.entities.TutorReviews;
import com.glob.ufriends.entities.TutorReviewsId;
import com.glob.ufriends.entities.TutorReviewsHelper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface TutorReviewsRepo extends JpaRepository<TutorReviews, TutorReviewsId>{

    @Query(value = "select * from TUTOR_REVIEWS tr where tr.USER_UID = ?1",
            nativeQuery = true)
    List<TutorReviews> findTutorReviewsMadeByUser(String userId);

    @Query(value = "select * from TUTOR_REVIEWS tr where tr.TUTOR_ID = ?1",
            nativeQuery = true)
    List<TutorReviews> findTutorReviewsByTutorID(String tutorId);
    
    // The USER_UID needs to be static or defined here in order to not have an invalid column name exception
    @Query(value = "select '117780905' as USER_UID, tr.TUTOR_ID,"
            + " (sum(tr.SCORE)/count(tr.SCORE)) as Score, 'COMMENTS' as COMMENTS"
            + " from TUTOR_REVIEWS tr group by tr.TUTOR_ID order by Score desc",
            nativeQuery = true)
    List<TutorReviews> getTutorsAverageReviewRating();

}