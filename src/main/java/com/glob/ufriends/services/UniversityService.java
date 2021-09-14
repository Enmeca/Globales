package com.glob.ufriends.services;



import com.glob.ufriends.entities.University;
import com.glob.ufriends.repositories.UniversityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UniversityService {

    @Autowired
    private UniversityRepo repository;

    public University saveUniversity(University obj){
        return repository.save(obj);
    }

    public List<University> getAllUniversities(){
        return repository.findAll();
    }

    public University getUniversity(String id){
        return repository.findById(id).orElse(null);
    }
}
