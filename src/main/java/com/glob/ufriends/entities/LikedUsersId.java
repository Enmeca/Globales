package com.glob.ufriends.entities;

import java.io.Serializable;

public class LikedUsersId implements  Serializable{

    private String user;
    private String likedUser;

    public LikedUsersId(){

    }

    public LikedUsersId(String user, String likedUser) {
        this.user = user;
        this.likedUser = likedUser;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + user.length();
        hash = 41 * hash + likedUser.length();
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final LikedUsersId other = (LikedUsersId) obj;
        if (this.user != other.user) {
            return false;
        }
        if (this.likedUser != other.likedUser) {
            return false;
        }
        return true;
    }

}
