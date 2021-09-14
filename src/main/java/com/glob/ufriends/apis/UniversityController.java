package com.glob.ufriends.apis;

import com.glob.ufriends.entities.University;
import com.glob.ufriends.services.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "/api/v1/university")
public class UniversityController {

    @Autowired
    private UniversityService service;

    @PostMapping
    public University addUniversity(@RequestBody University University){
        return service.saveUniversity(University);
    }

    @GetMapping
    public List<University> findAllUniversities(){
        return service.getAllUniversities();
    }

    @GetMapping(path = "{id}")
    public University findUniversityById(@PathVariable("id") String id) {
        return service.getUniversity(id);
    }
}
