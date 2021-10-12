package com.glob.ufriends.repositories;

import java.util.List;

import com.glob.ufriends.entities.LikedUsers;
import com.glob.ufriends.entities.LikedUsersId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


public interface LikedUsersRepo extends JpaRepository<LikedUsers, LikedUsersId>{

    @Query(value = "select * from LIKED_USERS lu where lu.USER_UID = ?1",
            nativeQuery = true)
    List<LikedUsers> findLikedUsersByUserID(String userId);

    @Query(value = "select * from LIKED_USERS lu where lu.LIKED_USER_UID = ?1",
            nativeQuery = true)
    List<LikedUsers> findLikedUsersByLikedUserID(String likedUserId);
    
    @Query(value = "select * from LIKED_USERS lu where lu.USER_UID = ?1 and lu.LIKED_USER_UID = ?2",
            nativeQuery = true)
    LikedUsers getLikedUser(String userId, String likedUserId);
    
    @Modifying
    @Query(value = "delete from LIKED_USERS lu where lu.USER_UID = ?1 and lu.LIKED_USER_UID = ?2",
            nativeQuery = true)
    void deleteLikedUserById(String userId,String likedUserId);

}
