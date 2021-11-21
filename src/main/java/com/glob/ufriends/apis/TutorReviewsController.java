package com.glob.ufriends.apis;

import com.glob.ufriends.entities.TutorReviews;
import com.glob.ufriends.entities.TutorReviewsHelper;
import com.glob.ufriends.entities.User;
import com.glob.ufriends.services.TutorReviewsService;
import com.glob.ufriends.services.UserService;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "/api/v1/tutorReviews")
@RestController
public class TutorReviewsController {

    @Autowired
    private TutorReviewsService service;
    
    @Autowired
    private UserService userService;

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

    // Returns a list of TutorReviewsHelper ordered by the average rating of the tutor in descending order
    /* The Objects have this structure:
    *  {
    *     tutor: Object, // An user Object with the information of the tutor
    *     averageRating: float // The average rating of the tutor. It is 0.0 if the tutor has not been reviewed yet
    *  }
    */
    @GetMapping(path = "/all/averageRating")
    public List<TutorReviewsHelper> getTutorsAverageReviewRating() {
        List<TutorReviewsHelper> tutorsWithAVGReviewRating = new ArrayList<>();
        List<TutorReviews> tutorsReviewed = service.getTutorsAverageReviewRating();
        List<User> tutorsNotReviewed = userService.getAllTutorsThatHaveNotBeenReviewedYet();
        
        for(int i = 0; i < tutorsReviewed.size(); i++) {
            TutorReviews tr = tutorsReviewed.get(i);
            tutorsWithAVGReviewRating.add(new TutorReviewsHelper(tr.getTutor(), tr.getScore()));
        }
        
        for(int j = 0; j < tutorsNotReviewed.size(); j++) {
            User user = tutorsNotReviewed.get(j);
            tutorsWithAVGReviewRating.add(new TutorReviewsHelper(user, 0.0f));
        }
           
        return tutorsWithAVGReviewRating;
    }

    @Transactional
    @DeleteMapping
    public void deleteLikedUser(@RequestBody TutorReviews tutorReviews) {
        service.deleteTutorReviews(tutorReviews);
    }

}
