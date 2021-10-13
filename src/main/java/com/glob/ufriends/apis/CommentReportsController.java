package com.glob.ufriends.apis;

import com.glob.ufriends.entities.CommentReports;
import com.glob.ufriends.services.CommentReportsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;

@RequestMapping(value = "/api/v1/commentReports")
@RestController
public class CommentReportsController {

    @Autowired
    private CommentReportsService service;

    @PostMapping
    public CommentReports addCommentReports(@RequestBody CommentReports commentReports) {
        return service.saveCommentReports(commentReports);
    }

    @GetMapping
    public List<CommentReports> findAllCommentReportss() {
        return service.getAllCommentReports();
    }
    
    @GetMapping(path = "/orderByDateDesc")
    public List<CommentReports> getCommentReportsOrderedByCDate() {
        return service.getCommentReportsOrderedByCDate();
    }

    @Transactional
    @DeleteMapping(path = "/del/{commentId}/{userId}")
    public void deleteCommentReportsById(@PathVariable("commentId") int commentId,
            @PathVariable("userId") String userId){
        service.deleteCommentReportsById(commentId,userId);
    }
    
    @Transactional    
    @DeleteMapping(path = "/delByComment/{commentId}")
    public void deleteCommentReportsByCommentId(@PathVariable("commentId") int commentId){
        service.deleteCommentReportsByCommentId(commentId);
    }
    
}
