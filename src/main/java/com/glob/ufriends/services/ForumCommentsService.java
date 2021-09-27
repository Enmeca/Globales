package com.glob.ufriends.services;

import com.glob.ufriends.entities.ForumComments;
import com.glob.ufriends.repositories.ForumCommentsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ForumCommentsService {
    @Autowired
    private ForumCommentsRepo repository;

    public ForumComments saveForumComments(ForumComments obj){
        return repository.save(obj);
    }
    
    public List<ForumComments> getAllForumComments(){
        return repository.findAll();
    }

    public List<ForumComments> findForumCommentsByForumID(int forumId){
        return repository.findForumCommentsByForumID(forumId);
    }
    
    public List<ForumComments> getForumCommentsOrderedByCDate(){
        return repository.getForumCommentsOrderedByCDate();
    }

    public ForumComments getForumComments(int id){
        return repository.findById(id).orElse(null);
    }

    public void deleteForumComments(ForumComments obj){
        repository.delete(obj);
    }

}
