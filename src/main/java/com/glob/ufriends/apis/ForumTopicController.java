package com.glob.ufriends.apis;

import com.glob.ufriends.entities.ForumTopic;
import com.glob.ufriends.services.ForumTopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "/api/v1/forumTopic")
@RestController
public class ForumTopicController {

    @Autowired
    private ForumTopicService service;

    @PostMapping
    public ForumTopic addForumTopic(@RequestBody ForumTopic forumTopic) {
        return service.saveForumTopic(forumTopic);
    }

    @GetMapping
    public List<ForumTopic> findAllForumTopics() {
        return service.getAllForumTopics();
    }

    @GetMapping(path = "{id}")
    public ForumTopic findForumTopicById(@PathVariable("id") int id) {
        return service.getForumTopic(id);
    }

    @GetMapping(path = "/orderByDateDesc")
    public List<ForumTopic> findAllTutors() {
        return service.getForumTopicsOrderedByCDate();
    }

    @DeleteMapping
    public void deleteForumTopic(@RequestBody ForumTopic forumTopic){
        service.deleteForumTopic(forumTopic);
    }
    
}
