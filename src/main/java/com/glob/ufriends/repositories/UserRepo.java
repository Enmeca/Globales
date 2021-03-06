package com.glob.ufriends.repositories;

import com.glob.ufriends.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import org.springframework.data.jpa.repository.Modifying;

public interface UserRepo extends JpaRepository<User, String>{

    // Could be used for login
    @Query(value = "select * from USERS u where u.ID = ?1 and u.PASSWORD = ?2",
            nativeQuery = true)
    User getUserByIdPassword(String id, String password);

    // Another option for login. I havent tested if there is problem with User instead of List<User>
    @Query(value = "select * from USERS u where u.EMAIL = ?1 and u.PASSWORD = ?2",
            nativeQuery = true)
    User getUserByEmailPassword(String email, String password);

    // Method used to update last connected of a user to sysdate
    @Modifying
    @Query(value = "update USERS u set u.LAST_CONNECTED = sysdate where u.ID = ?1",
            nativeQuery = true)
    void updateUserLastConnected(String id);
    
    // Method used to update active status of a user
    @Modifying
    @Query(value = "update USERS u set u.IS_ACTIVE = ?1 where u.ID = ?2",
            nativeQuery = true)
    void updateUserIsActive(int isActive, String id);
    
    // To get all users that are tutors
    @Query(value = "select * from USERS u where u.IS_TUTOR = 1",
            nativeQuery = true)
    List<User> getAllTutors();
    
    // To get all users that are tutors that have not been reviewed yet by anyone
    @Query(value = "select * from USERS where IS_TUTOR = 1"
            + " and ID not in (select TUTOR_ID from TUTOR_REVIEWS group by TUTOR_ID)",
            nativeQuery = true)
    List<User> getAllTutorsThatHaveNotBeenReviewedYet();
    
    // To get all users that have not been liked by the user yet and that are not admins
    @Query(value = "select * from USERS where ID in (select ID from USERS"
            + " where ID <> ?1 minus select LIKED_USER_UID from LIKED_USERS where"
            + " USER_UID = ?1) and IS_ADMIN = 0",
            nativeQuery = true)
    List<User> getAllUsersThatHaveNotBeenLikedByUser(String id);

}
