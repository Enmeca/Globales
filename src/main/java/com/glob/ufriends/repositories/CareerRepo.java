package com.glob.ufriends.repositories;


import com.glob.ufriends.entities.Career;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CareerRepo extends JpaRepository<Career, String> {
}
