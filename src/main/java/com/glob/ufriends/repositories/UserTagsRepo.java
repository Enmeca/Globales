package com.glob.ufriends.repositories;

import java.util.List;

import com.glob.ufriends.entities.UserTags;
import com.glob.ufriends.entities.UserTagsId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


public interface UserTagsRepo extends JpaRepository<UserTags, UserTagsId>{

    @Query(value = "select * from USER_TAGS ut where ut.USER_UID = ?1",
            nativeQuery = true)
    List<UserTags> findUserTagsByUserID(String userId);

    @Query(value = "select * from USER_TAGS ut where ut.TAG_ID = ?1",
            nativeQuery = true)
    List<UserTags> findUsersTagsByTag(String tag);

    @Modifying
    @Query(value = "delete from USER_TAGS ut where ut.USER_UID = ?1 and ut.TAG_ID = ?2",
            nativeQuery = true)
    void deleteUsersTagByUserIdTagId(String userId,String tagId);

}
