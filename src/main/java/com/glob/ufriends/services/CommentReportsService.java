package com.glob.ufriends.services;

import com.glob.ufriends.entities.CommentReports;
import com.glob.ufriends.entities.CommentReportsId;
import com.glob.ufriends.repositories.CommentReportsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentReportsService {
    @Autowired
    private CommentReportsRepo repository;

    public CommentReports saveCommentReports(CommentReports obj){
        return repository.save(obj);
    }
    
    public List<CommentReports> getAllCommentReports(){
        return repository.findAll();
    }
   
    public List<CommentReports> getCommentReportsOrderedByCDate(){
        return repository.getCommentReportsOrderedByCDate();
    }

    public CommentReports getCommentReports(CommentReportsId id){
        return repository.findById(id).orElse(null);
    }

    public void deleteCommentReports(CommentReports obj){
        repository.delete(obj);
    }
    
    public void deleteCommentReportsById(int commentId, String userId){
        repository.deleteCommentReportsById(commentId,userId);
    }
    
    public void deleteCommentReportsByCommentId(int commentId){
        repository.deleteCommentReportsByCommentId(commentId);
    }

}
