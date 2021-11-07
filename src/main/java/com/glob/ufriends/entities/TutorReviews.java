package com.glob.ufriends.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@IdClass(TutorReviewsId.class)
@Table(name = "TUTOR_REVIEWS")
public class TutorReviews {

    @Id
    @ManyToOne
    @JoinColumn(name = "USER_UID", nullable = false)
    private User user;

    @Id
    @ManyToOne
    @JoinColumn(name = "TUTOR_ID", nullable = false)
    private User tutor;

    @Column(name = "SCORE")
    private float score;

    @Column(name = "COMMENTS")
    private String comments;

}
