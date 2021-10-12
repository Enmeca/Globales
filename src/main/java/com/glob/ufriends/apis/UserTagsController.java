package com.glob.ufriends.apis;

import com.glob.ufriends.entities.UserTags;
import com.glob.ufriends.services.UserTagsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "/api/v1/userTags")
@RestController
public class UserTagsController {

    @Autowired
    private UserTagsService service;

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
