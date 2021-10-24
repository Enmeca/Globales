package com.glob.ufriends.repositories;


import com.glob.ufriends.entities.UserPhoto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface UserPhotoRepo extends JpaRepository<UserPhoto, String> {

    // Method that only returns the photo itself. Not tested yet
    @Query(value = "select PROFILE_PIC from USER_PHOTOS where USER_UID = ?1",
            nativeQuery = true)
    byte[] getPhotoByUserID(String id);

    // Method to update only the profile picture
    @Modifying
    @Query(value = "update USER_PHOTOS up set up.PROFILE_PIC = ?1 where up.USER_UID = ?2",
            nativeQuery = true)
    void updateUserProfilePic(byte[] newPhoto,String userId);
}
