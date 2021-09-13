package com.glob.ufriends.apis;

import com.glob.ufriends.entities.UserPhoto;
import com.glob.ufriends.services.UserPhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "/api/v1/userPhoto")
@RestController
public class UserPhotoController {

    @Autowired
    private UserPhotoService service;

    @PostMapping
    public UserPhoto addUserPhoto(@RequestBody UserPhoto userPhoto) {
        return service.saveUserPhoto(userPhoto);
    }

    @Transactional
    @PutMapping(path = "/updatePhoto")
    public void updateUserProfilePic(@RequestBody UserPhoto userPhoto) {
        service.updateUserProfilePic(userPhoto.getProfilePic(),userPhoto.getId());
    }

    @GetMapping
    public List<UserPhoto> findAllUserPhotos() {
        return service.getAllUserPhotos();
    }

    @GetMapping(path = "{id}")
    public UserPhoto findUserPhoto(@PathVariable("id") String id) {
        return service.getUserPhoto(id);
    }

    @GetMapping(path = "/photo/{id}")
    public UserPhoto findPhotoByUserId(@PathVariable("id") String id) {
        return service.getPhotoByUserID(id);
    }

    @DeleteMapping
    public void deleteUser(@RequestBody UserPhoto userPhoto){
        service.deleteUserPhoto(userPhoto);
    }

}
