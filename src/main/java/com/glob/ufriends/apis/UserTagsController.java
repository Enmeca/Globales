package com.glob.ufriends.apis;

import com.glob.ufriends.entities.UserTags;
import com.glob.ufriends.entities.UserWithTags;
import com.glob.ufriends.entities.Tag;
import com.glob.ufriends.entities.User;
import com.glob.ufriends.services.UserTagsService;
import com.glob.ufriends.services.UserService;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "/api/v1/userTags")
@RestController
public class UserTagsController {

    @Autowired
    private UserTagsService service;
    
    @Autowired
    private UserService userService;

    @PostMapping
    public UserTags addUserTags(@RequestBody UserTags userTags) {
        return service.saveUserTags(userTags);
    }

    @Transactional
    @PostMapping(path = "/multiple")
    public void addMultipleUserTags(@RequestBody List<UserTags> userTags){
        for(int i = 0; i< userTags.size(); i++){
            service.saveUserTags(userTags.get(i));
        }
    }

    @GetMapping
    public List<UserTags> findAllUserTags() {
        return service.getAllUserTags();
    }

    @GetMapping(path = "/user/{id}")
    public List<UserTags> findUserTagsByUserId(@PathVariable("id") String id) {
        return service.findUserTagsByUserID(id);
    }

    // Returns the user tags of a specified user as an array of tags objects instead of user tag objects
    // The parameter is the user id
    @GetMapping(path = "/onlyTags/byUserId/{id}")
    public List<Tag> findOnlyTagsByUserId(@PathVariable("id") String id) {
        return service.findOnlyTagsByUserId(id);
    }

    // Returns the user tags of a specified user as an array of strings instead of user tag objects
    // The parameter is the user id
    @GetMapping(path = "/onlyTags/asStrings/byUserId/{id}")
    public List<String> findOnlyTagsAsStringsByUserId(@PathVariable("id") String id) {
        return service.findOnlyTagsAsStringsByUserId(id);
    }
    
    // Returns a list of userWithTags of
    // the users that have not been liked by the specified userId
    // The parameter is the logged in user id.
    @GetMapping(path = "/userWithTags/notLiked/byUserId/{id}")
    public List<UserWithTags> findUserWithTagsNotLikedByUser(@PathVariable("id") String id) {
        List<User> usersNotLiked = userService.getAllUsersThatHaveNotBeenLikedByUser(id);
        List<UserWithTags> usersWithTagsNotLiked = new ArrayList<>();
        for(int i = 0; i< usersNotLiked.size(); i++){
            String userId = usersNotLiked.get(i).getId();
            UserWithTags userWTags = new UserWithTags();
            userWTags.setUser(usersNotLiked.get(i));
            userWTags.setTags(service.findOnlyTagsAsStringsByUserId(userId));
            usersWithTagsNotLiked.add(userWTags);
        }
        return usersWithTagsNotLiked;
    }

    @GetMapping(path = "/tag/{id}")
    public List<UserTags> findUserTagsByTag(@PathVariable("id") String id) {
        return service.findUserTagsByTagID(id);
    }

    @Transactional
    @DeleteMapping(path = "/del/{id}/{tag}")
    public void deleteUserTag(@PathVariable("id") String id, @PathVariable("tag") String tag){
        service.deleteUserTagsByParams(id,tag);
    }

    @Transactional
    @DeleteMapping(path = "/multiple")
    public void deleteMultipleUserTags(@RequestBody List<UserTags> userTags){
        for(int i = 0; i< userTags.size(); i++){
            UserTags ut = userTags.get(i);
            service.deleteUserTagsByParams(ut.getUser().getId(),
                    ut.getTag().getName());
        }
    }
}
