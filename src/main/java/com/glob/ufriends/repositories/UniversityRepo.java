package com.glob.ufriends.repositories;

import com.glob.ufriends.entities.University;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UniversityRepo extends JpaRepository <University, String>{
}
