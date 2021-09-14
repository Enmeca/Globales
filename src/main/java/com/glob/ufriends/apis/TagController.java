package com.glob.ufriends.apis;

import com.glob.ufriends.entities.Tag;
import com.glob.ufriends.services.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "/api/v1/tag")
@RestController
public class TagController {

    @Autowired
    private TagService service;

    @PostMapping
    public Tag addTag(@RequestBody Tag Tag){
        return service.saveTag(Tag);
    }

    @GetMapping
    public List<Tag> findAllTags(){
        return service.getAllTags();
    }

    @GetMapping(path = "{id}")
    public Tag findTagById(@PathVariable("id") String id) {
        return service.getTag(id);
    }
}
