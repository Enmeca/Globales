package com.glob.ufriends.apis;

import com.glob.ufriends.entities.ForumComments;
import com.glob.ufriends.services.ForumCommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "/api/v1/forumComments")
@RestController
public class ForumCommentsController {

    @Autowired
    private ForumCommentsService service;

    @PostMapping
    public ForumComments addForumComments(@RequestBody ForumComments forumComments) {
        return service.saveForumComments(forumComments);
    }

    @GetMapping
    public List<ForumComments> findAllForumComments() {
        return service.getAllForumComments();
    }

    @GetMapping(path = "{id}")
    public ForumComments findForumCommentsById(@PathVariable("id") int id) {
        return service.getForumComments(id);
    }
    
    // The parameter is the forumUID
    @GetMapping(path = "/forum/{id}")
    public ForumComments findForumCommentsByForumID(@PathVariable("id") int id) {
        return service.getForumComments(id);
    }

    @GetMapping(path = "/orderByDateDesc")
    public List<ForumComments> findAllForumCommentsOrderByDate() {
        return service.getForumCommentsOrderedByCDate();
    }

    @DeleteMapping
    public void deleteForumComments(@RequestBody ForumComments forumComments){
        service.deleteForumComments(forumComments);
    }
    
}
