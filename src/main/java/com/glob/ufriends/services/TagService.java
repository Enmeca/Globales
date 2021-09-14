package com.glob.ufriends.services;


import com.glob.ufriends.entities.Tag;
import com.glob.ufriends.repositories.TagRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {

    @Autowired
    private TagRepo repository;

    public Tag saveTag(Tag obj){
        return repository.save(obj);
    }

    public List<Tag> getAllTags(){
        return repository.findAll();
    }

    public Tag getTag(String id){
        return repository.findById(id).orElse(null);
    }

}
