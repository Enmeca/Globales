package com.glob.ufriends.entities;

import java.io.Serializable;

public class UserTagsId implements  Serializable{

    private String user;
    private String tag;

    public UserTagsId(){

    }

    public UserTagsId(String user, String tag) {
        this.user = user;
        this.tag = tag;
    }

    /*@Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + this.user;
        hash = 41 * hash + this.tag;
        return hash;
    }*/

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
        final UserTagsId other = (UserTagsId) obj;
        if (this.user != other.user) {
            return false;
        }
        if (this.tag != other.tag) {
            return false;
        }
        return true;
    }

}
