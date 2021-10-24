package com.glob.ufriends.apis;

import com.glob.ufriends.entities.User;
import com.glob.ufriends.entities.UserPhoto;
import com.glob.ufriends.entities.UserPhotoHelper;
import com.glob.ufriends.services.UserPhotoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


import java.util.Base64;
import java.util.List;

@RequestMapping(value = "/api/v1/userPhoto")
@RestController
public class UserPhotoController {

    @Autowired
    private UserPhotoService service;
/*
    @PostMapping
    public UserPhoto addUserPhoto(@RequestBody UserPhoto userPhoto) {
        return service.saveUserPhoto(userPhoto);
    }
*/
    /* SEND POST WITH THIS STRUCTURE FOR:
    * {
    *   "id": String,
    *   "base64Photo": String // Do not send it with 'data:image/png;base64,'
    * }
    *
    *
    *
    * */

    @PostMapping
    public UserPhoto addUserPhoto(@RequestBody UserPhotoHelper userPhotoH) {
        byte[] decodedByte = Base64.getDecoder().decode(userPhotoH.getBase64Photo());
        UserPhoto userPhoto = new UserPhoto();
        userPhoto.setId(userPhotoH.getUserId());
        userPhoto.setProfilePic(decodedByte);
        return service.saveUserPhoto(userPhoto);
    }

    @Transactional
    @PutMapping(path = "/updatePhoto")
    public void updateUserProfilePic(@RequestBody UserPhotoHelper userPhotoH) {
        byte[] decodedByte = Base64.getDecoder().decode(userPhotoH.getBase64Photo());
        service.updateUserProfilePic(decodedByte,userPhotoH.getUserId());
    }

    @GetMapping
    public List<UserPhoto> findAllUserPhotos() {
        return service.getAllUserPhotos();
    }

    // Use this to get the whole object: {id: String, profilePic: String in base64}
    @GetMapping(path = "{id}") // The id is the user id
    public UserPhoto findUserPhoto(@PathVariable("id") String id) {
        return service.getUserPhoto(id);
    }

    // Use this to only get the profile pic (Should be bytecode)
    @GetMapping(path = "/photo/{id}") // The id is the user id
    public byte[] findPhotoByUserId(@PathVariable("id") String id) {
        return service.getPhotoByUserID(id);
    }

    @Transactional
    @DeleteMapping
    public void deleteUser(@RequestBody UserPhoto userPhoto){
        service.deleteUserPhoto(userPhoto);
    }

}
