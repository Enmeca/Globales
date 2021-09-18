package com.glob.ufriends.apis;

import com.glob.ufriends.entities.ResourceNotFoundException;
import com.glob.ufriends.entities.User;
import com.glob.ufriends.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "/api/v1/user")
@RestController
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping
    public User addUser(@RequestBody User user) {
        return service.saveUser(user);
    }

    @PostMapping(path = "/loginById")
    public User loginById(@RequestBody User user){
        return service.loginById(user.getId(),user.getPassword());
    }

    @PostMapping(path = "/loginByEmail")
    public User loginByEmail(@RequestBody User user){
        User loggedUser = service.loginByEmail(user.getEmail(),user.getPassword());
        if(loggedUser == null)
            throw new ResourceNotFoundException();

        return loggedUser;
    }

    /*
    Method to login using id or email and password. Just put the id or email in the id space of the json.

    {
        "id": userId || userEmail,
        "password": userPassword
    }

    Returns the User or error 404.
    */
    @PostMapping(path = "/login")
    public User login(@RequestBody User user){

        // First we check if the input was the correct id and password
        User loggedUser = service.loginById(user.getId(),user.getPassword());

        // If the user was found then the method ends
        if(loggedUser != null )
            return loggedUser;

        // If the user was not found with the id, then check if the input was the email
        loggedUser = service.loginByEmail(user.getId(),user.getPassword());

        // If its null then return 404
        if(loggedUser == null)
            throw new ResourceNotFoundException();

        // Return loggedUser if it was found with the email
        return loggedUser;
    }

    @GetMapping
    public List<User> findAllUsers() {
        return service.getAllUsers();
    }

    @GetMapping(path = "{id}")
    public User findUserById(@PathVariable("id") String id) {
        return service.getUser(id);
    }

    @GetMapping(path = "/AllTutors")
    public List<User> findAllTutors() {
        return service.getAllTutors();
    }

    @DeleteMapping
    public void deleteUser(@RequestBody User user){
        service.deleteUser(user);
    }
    
}
