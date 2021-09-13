package com.glob.ufriends.services;

import com.glob.ufriends.entities.UserTags;
import com.glob.ufriends.entities.UserTagsId;
import com.glob.ufriends.repositories.UserTagsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserTagsService {
    @Autowired
    private UserTagsRepo repository;

    public UserTags saveUserTags(UserTags obj){
        return repository.save(obj);
    }

    public List<UserTags> getAllUserTags(){
        return repository.findAll();
    }

    public UserTags getUserTag(UserTagsId id){
        return repository.findById(id).orElse(null);
    }

    public List<UserTags> findUserTagsByUserID(String id){
        return repository.findUserTagsByUserID(id);
    }

    public List<UserTags> findUserTagsByTagID(String id){
        return repository.findUsersTagsByTag(id);
    }

    public void deleteUserTags(UserTags obj){
        repository.delete(obj);
    }

    public void deleteUserTagsByParams(String userId, String tagId){
        repository.deleteUsersTagByUserIdTagId(userId,tagId);
    }

}
