package com.glob.ufriends.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TutorReviewsHelper {

    private User tutor;
    private float averageRating;

}
