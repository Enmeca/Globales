package com.glob.ufriends.repositories;

import com.glob.ufriends.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepo extends JpaRepository<User, String>{

    // Could be used for login
    @Query(value = "select * from USERS u where u.ID = ?1 and u.PASSWORD = ?2",
            nativeQuery = true)
    User getUserByIdPassword(String id, String password);

    // Another option for login. I havent tested if there is problem with User instead of List<User>
    @Query(value = "select * from USERS u where u.EMAIL = ?1 and u.PASSWORD = ?2",
            nativeQuery = true)
    User getUserByEmailPassword(String email, String password);

    // To get all users that are tutors
    @Query(value = "select * from USERS u where u.IS_TUTOR = 1",
            nativeQuery = true)
    List<User> getAllTutors();

}
