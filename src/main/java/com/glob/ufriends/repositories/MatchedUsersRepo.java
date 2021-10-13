package com.glob.ufriends.repositories;

import com.glob.ufriends.entities.MatchedUsers;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MatchedUsersRepo extends JpaRepository<MatchedUsers, Integer> {
    
    @Query(value = "select * from MATCHED_USERS mu where mu.USER_UID = ?1",
            nativeQuery = true)
    List<MatchedUsers> findMatchedUsersByUserID(String userId);
    
    @Query(value = "select * from MATCHED_USERS mu where mu.MATCHED_USER_UID = ?1",
            nativeQuery = true)
    List<MatchedUsers> findMatchedUsersByMatchedUserID(String matchedUserId);
     
}
