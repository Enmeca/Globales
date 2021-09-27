package com.glob.ufriends.services;

import com.glob.ufriends.entities.LikedUsers;
import com.glob.ufriends.entities.LikedUsersId;
import com.glob.ufriends.repositories.LikedUsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LikedUsersService {
    @Autowired
    private LikedUsersRepo repository;

    public LikedUsers saveLikedUsers(LikedUsers obj){
        return repository.save(obj);
    }

    public List<LikedUsers> getAllLikedUsers(){
        return repository.findAll();
    }

    public LikedUsers getUserTag(LikedUsersId id){
        return repository.findById(id).orElse(null);
    }

    public List<LikedUsers> findLikedUsersByUserID(String userId){
        return repository.findLikedUsersByUserID(userId);
    }

    public List<LikedUsers> findLikedUsersByLikedUserID(String likedUserId){
        return repository.findLikedUsersByLikedUserID(likedUserId);
    }

    public void deleteLikedUsers(LikedUsers obj){
        repository.delete(obj);
    }

    public void deleteLikedUserById(String userId, String likedUserId){
        repository.deleteLikedUserById(userId,likedUserId);
    }

}
