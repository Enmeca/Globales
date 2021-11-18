package com.glob.ufriends.entities;

import java.io.Serializable;

public class TutorReviewsId implements  Serializable{

    private String user;
    private String tutor;

    public TutorReviewsId(){

    }

    public TutorReviewsId(String user, String tutor) {
        this.user = user;
        this.tutor = tutor;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + user.length();
        hash = 41 * hash + tutor.length();
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
        final TutorReviewsId other = (TutorReviewsId) obj;
        if (this.user != other.user) {
            return false;
        }
        if (this.tutor != other.tutor) {
            return false;
        }
        return true;
    }

}
