package com.glob.ufriends.services;

import com.glob.ufriends.entities.User;
import com.glob.ufriends.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepo repository;

    public User saveUser(User obj){
        return repository.save(obj);
    }

    public List<User> getAllUsers(){
        return repository.findAll();
    }

    public User getUser(String id){
        return repository.findById(id).orElse(null);
    }

    public void deleteUser(User obj){
        repository.delete(obj);
    }

    public User loginById(String id, String password){
        return repository.getUserByIdPassword(id,password);
    }

    public User loginByEmail(String email, String password){
        return repository.getUserByEmailPassword(email,password);
    }

    public List<User> getAllTutors(){
        return repository.getAllTutors();
    }
}
