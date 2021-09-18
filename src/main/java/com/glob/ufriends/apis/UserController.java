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

    @PostMapping(path = "/loginV1")
    public User loginById(@RequestBody User user){
        return service.loginById(user.getId(),user.getPassword());
    }

    @PostMapping(path = "/loginV2")
    public User loginByEmail(@RequestBody User user){
        User loggedUser = service.loginByEmail(user.getEmail(),user.getPassword());
        if(loggedUser == null)
            throw new ResourceNotFoundException();

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
