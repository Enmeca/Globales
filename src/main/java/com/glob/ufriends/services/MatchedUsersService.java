package com.glob.ufriends.services;

import com.glob.ufriends.entities.MatchedUsers;
import com.glob.ufriends.repositories.MatchedUsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatchedUsersService {
    @Autowired
    private MatchedUsersRepo repository;

    public MatchedUsers saveMatchedUsers(MatchedUsers obj){
        return repository.save(obj);
    }
    
    public List<MatchedUsers> getAllMatchedUsers(){
        return repository.findAll();
    }

    public List<MatchedUsers> findMatchedUsersByUserID(String userId){
        return repository.findMatchedUsersByUserID(userId);
    }
    
    public List<MatchedUsers> findMatchedUsersByMatchedUserID(String matchedUserId){
        return repository.findMatchedUsersByUserID(matchedUserId);
    }

    public MatchedUsers getMatchedUsers(int id){
        return repository.findById(id).orElse(null);
    }

    public void deleteMatchedUsers(MatchedUsers obj){
        repository.delete(obj);
    }

}
