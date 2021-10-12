package com.glob.ufriends.entities;

import java.io.Serializable;

public class CommentReportsId implements  Serializable{

    private int comment;
    private String user;

    public CommentReportsId(){

    }

    public CommentReportsId(int comment, String user) {
        this.comment = comment;
        this.user = user;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + user.length();
        hash = 41 * hash + comment;
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
        final CommentReportsId other = (CommentReportsId) obj;
        if (this.user != other.user) {
            return false;
        }
        if (this.comment != other.comment) {
            return false;
        }
        return true;
    }

}
