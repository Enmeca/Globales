package com.glob.ufriends.services;

import com.glob.ufriends.entities.ForumTopic;
import com.glob.ufriends.repositories.ForumTopicRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ForumTopicService {
    @Autowired
    private ForumTopicRepo repository;

    public ForumTopic saveForumTopic(ForumTopic obj){
        return repository.save(obj);
    }

    public List<ForumTopic> getAllForumTopics(){
        return repository.findAll();
    }
    
    public List<ForumTopic> getForumTopicsOrderedByCDate(){
        return repository.getForumTopicsOrderedByCDate();
    }

    public ForumTopic getForumTopic(int id){
        return repository.findById(id).orElse(null);
    }
    
    public ForumTopic updateForumTopicCommentsQuantity(int comments, int forumId){
        repository.updateForumTopicCommentsQuantity(comments, forumId);
        return repository.findById(forumId).orElse(null);
    }

    public void deleteForumTopic(ForumTopic obj){
        repository.delete(obj);
    }

}
