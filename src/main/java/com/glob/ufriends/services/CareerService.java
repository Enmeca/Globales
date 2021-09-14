package com.glob.ufriends.services;

import com.glob.ufriends.entities.Career;
import com.glob.ufriends.repositories.CareerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CareerService {

    @Autowired
    private CareerRepo repository;

    public Career saveCareer(Career obj){
        return repository.save(obj);
    }

    public List<Career> getAllCareers(){
        return repository.findAll();
    }

    public Career getCareer(String id){
        return repository.findById(id).orElse(null);
    }
}
