package com.glob.ufriends.apis;

import com.glob.ufriends.entities.Career;
import com.glob.ufriends.services.CareerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "/api/v1/career")
@RestController
public class CareerController {

    @Autowired
    private CareerService service;

    @PostMapping
    public Career addCareer(@RequestBody Career career){
        return service.saveCareer(career);
    }

    @GetMapping
    public List<Career> findAllCareers(){
        return service.getAllCareers();
    }

    @GetMapping(path = "{id}")
    public Career findCareerById(@PathVariable("id") String id) {
        return service.getCareer(id);
    }

}
