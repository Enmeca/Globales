package com.glob.ufriends.services;

import com.glob.ufriends.entities.TutorReviews;
import com.glob.ufriends.entities.TutorReviewsHelper;
import com.glob.ufriends.repositories.TutorReviewsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TutorReviewsService {
    @Autowired
    private TutorReviewsRepo repository;

    public TutorReviews saveTutorReviews(TutorReviews obj){
        return repository.save(obj);
    }

    public List<TutorReviews> getAllTutorReviews(){
        return repository.findAll();
    }

    public List<TutorReviews> findTutorReviewsMadeByUser(String userId){
        return repository.findTutorReviewsMadeByUser(userId);
    }

    public List<TutorReviews> findTutorReviewsByTutorID(String tutorId){
        return repository.findTutorReviewsByTutorID(tutorId);
    }
    
    public List<TutorReviewsHelper> getTutorsAverageReviewRating(){
        return repository.getTutorsAverageReviewRating();
    }
    
    public void deleteTutorReviews(TutorReviews obj){
        repository.delete(obj);
    }

}
