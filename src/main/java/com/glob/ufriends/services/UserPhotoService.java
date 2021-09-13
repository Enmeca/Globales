package com.glob.ufriends.services;

import com.glob.ufriends.entities.UserPhoto;
import com.glob.ufriends.repositories.UserPhotoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserPhotoService {
    @Autowired
    private UserPhotoRepo repository;

    public UserPhoto saveUserPhoto(UserPhoto obj){
        return repository.save(obj);
    }

    public List<UserPhoto> getAllUserPhotos(){
        return repository.findAll();
    }

    public UserPhoto getUserPhoto(String id){
        return repository.findById(id).orElse(null);
    }

    public void updateUserProfilePic(byte[] newPhoto,String userId){
        repository.updateUserProfilePic(newPhoto,userId);
    }

    public UserPhoto getPhotoByUserID(String id){
        return repository.getPhotoByUserID(id);
    }

    public void deleteUserPhoto(UserPhoto obj){
        repository.delete(obj);
    }

}
