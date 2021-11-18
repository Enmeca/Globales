package com.glob.ufriends.apis;

import com.glob.ufriends.entities.TutorReviews;
import com.glob.ufriends.entities.TutorReviewsHelper;
import com.glob.ufriends.services.TutorReviewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "/api/v1/tutorReviews")
@RestController
public class TutorReviewsController {

    @Autowired
    private TutorReviewsService service;

    /*
     * JSON Object for Tutor Reviews (POST):
     *
     * {
     *   user: {id: String}, // User object that has the id of the user
     *   tutor: {id: String}, // User object that has the id of the tutor user
     *   score: float,
     *   comments: String
     * }
     *
     * */

    @Transactional
    @PostMapping
    public TutorReviews addTutorReviews(@RequestBody TutorReviews TutorReviews) {
        return service.saveTutorReviews(TutorReviews);
    }

    @GetMapping
    public List<TutorReviews> findAllTutorReviews() {
        return service.getAllTutorReviews();
    }

    // To get all reviews made by a specific user
    @GetMapping(path = "/user/{id}")
    public List<TutorReviews> findTutorReviewsMadeByUser(@PathVariable("id") String id) {
        return service.findTutorReviewsMadeByUser(id);
    }

    // To get all reviews of a specific tutor
    @GetMapping(path = "/tutor/{id}")
    public List<TutorReviews> findTutorReviewsByTutorID(@PathVariable("id") String id) {
        return service.findTutorReviewsByTutorID(id);
    }

    // WORK IN PROGRESS - THE QUERY WORKS FINE ON CLI BUT THERE ARE PROBLEMS CONVERTING IT TO AN OBJECT
    /*
    // To get a list of the tutors with their average rating
    @GetMapping(path = "/all/averageRating")
    public List<TutorReviewsHelper> getTutorsAverageReviewRating() {
        return service.getTutorsAverageReviewRating();
    }*/

    @Transactional
    @DeleteMapping
    public void deleteLikedUser(@RequestBody TutorReviews tutorReviews) {
        service.deleteTutorReviews(tutorReviews);
    }

}
