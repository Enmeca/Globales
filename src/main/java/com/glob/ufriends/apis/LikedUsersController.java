package com.glob.ufriends.apis;

import com.glob.ufriends.entities.LikedUsers;
import com.glob.ufriends.entities.MatchedUsers;
import com.glob.ufriends.entities.User;
import com.glob.ufriends.services.LikedUsersService;
import com.glob.ufriends.services.MatchedUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "/api/v1/likedUsers")
@RestController
public class LikedUsersController {

    @Autowired
    private LikedUsersService service;

    @Autowired
    private MatchedUsersService matchService;

    /*
     * JSON Object for Liked Users (POST):
     *
     * {
     *   user: {id: String}, // User object that has the id of the user
     *   likedUser: {id: String}, // User object that has the id of the liked user
     * }
     *
     * */

    @Transactional
    @PostMapping
    // This method also creates the match if both users have liked each other
    public LikedUsers addLikedUsers(@RequestBody LikedUsers likedUsers) {
        String userId = likedUsers.getUser().getId();
        String likedUserId = likedUsers.getLikedUser().getId();
        LikedUsers likedByOtherUser = service.getLikedUserByParams(likedUserId, userId);

        if(likedByOtherUser != null){
            MatchedUsers newMatchedUsers = new MatchedUsers();
            User user = new User();
            User likedUser = new User();
            user.setId(likedUserId);
            likedUser.setId(userId);
            // The user that liked second will be the matchedUser in the match. The one that liked first
            // will be the "user"
            newMatchedUsers.setUser(user);
            newMatchedUsers.setMatchedUser(likedUser);
            matchService.saveMatchedUsers(newMatchedUsers);

            LikedUsers lu = service.saveLikedUsers(likedUsers);
            LikedUsers lu2 = new LikedUsers();
            lu2.setUser(null);
            lu2.setLikedUser(lu.getLikedUser());
            return lu2;
        }

        return service.saveLikedUsers(likedUsers);
    }

    @Transactional
    @PostMapping(path = "/multiple")
    public void addMultipleLikedUsers(@RequestBody List<LikedUsers> likedUsers){
        for(int i = 0; i< likedUsers.size(); i++){
            service.saveLikedUsers(likedUsers.get(i));
        }
    }

    @GetMapping
    public List<LikedUsers> findAllLikedUsers() {
        return service.getAllLikedUsers();
    }

    @GetMapping(path = "/user/{id}")
    public List<LikedUsers> findLikedUsersByUserID(@PathVariable("id") String id) {
        return service.findLikedUsersByUserID(id);
    }

    @GetMapping(path = "/likedUser/{id}")
    public List<LikedUsers> findLikedUsersByLikedUserID(@PathVariable("id") String id) {
        return service.findLikedUsersByLikedUserID(id);
    }
    
    // This method will be used when a user likes another to check if the other one had also liked him before hand in
    // order to know if a match has been made
    @GetMapping(path = "/verify/{userId}/{likedUserId}")
    public LikedUsers getLikedUser(@PathVariable("userId") String userId, 
            @PathVariable("likedUserId") String likedUserId ) {
        return service.getLikedUserByParams(userId, likedUserId);
    }

    @Transactional
    @DeleteMapping(path = "/del/{user}/{likedUser}")
    public void deleteLikedUser(@PathVariable("user") String user, @PathVariable("likedUser") String likedUser){
        service.deleteLikedUserById(user,likedUser);
    }

    @Transactional
    @DeleteMapping(path = "/multiple")
    public void deleteUser(@RequestBody List<LikedUsers> likedUsers){
        for(int i = 0; i< likedUsers.size(); i++){
            LikedUsers lu = likedUsers.get(i);
            service.deleteLikedUserById(lu.getUser().getId(),
                    lu.getLikedUser().getId());
        }
    }
}
