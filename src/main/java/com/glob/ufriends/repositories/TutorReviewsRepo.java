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
    
    @Query(value = "select tr.TUTOR_ID, (sum(tr.SCORE)/count(tr.SCORE)) as Ratings from TUTOR_REVIEWS tr group by tr.TUTOR_ID order by Ratings desc",
            nativeQuery = true)
    List<TutorReviewsHelper> getTutorsAverageReviewRating();

}