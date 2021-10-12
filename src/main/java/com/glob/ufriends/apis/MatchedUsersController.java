package com.glob.ufriends.apis;

import com.glob.ufriends.entities.MatchedUsers;
import com.glob.ufriends.services.MatchedUsersService;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;

@RequestMapping(value = "/api/v1/matchedUsers")
@RestController
public class MatchedUsersController {

    @Autowired
    private MatchedUsersService service;

    @PostMapping
    public MatchedUsers addMatchedUsers(@RequestBody MatchedUsers matchedUsers) {
        return service.saveMatchedUsers(matchedUsers);
    }

    @GetMapping
    public List<MatchedUsers> findAllMatchedUsers() {
        return service.getAllMatchedUsers();
    }

    @GetMapping(path = "{id}")
    public MatchedUsers findMatchedUsersById(@PathVariable("id") int id) {
        return service.getMatchedUsers(id);
    }
    
    // This will be used to get the user "chats" The parameter is the user id
    @GetMapping(path = "/user/{id}")
    public List<MatchedUsers> findMatchedUsersByUserID(@PathVariable("id") String id) {
        List<MatchedUsers> userList = service.findMatchedUsersByUserID(id); // List where the user is the user "A" in the match
        List<MatchedUsers> matchedUserList = 
                service.findMatchedUsersByUserID(id); // List where the user is the user "B" in the match
        
        List<MatchedUsers> allMatchs = new ArrayList<>(); 
        allMatchs.addAll(userList);
        allMatchs.addAll(matchedUserList);
        
        return allMatchs; // Returns all the matchs where the user is present
        
    }

    @Transactional
    @DeleteMapping
    public void deleteMatchedUsers(@RequestBody MatchedUsers matchedUsers){
        service.deleteMatchedUsers(matchedUsers);
    }
    
}
