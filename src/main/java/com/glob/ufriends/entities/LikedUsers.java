package com.glob.ufriends.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@IdClass(LikedUsersId.class)
@Table(name = "LIKED_USERS")
public class LikedUsers {

    @Id
    @ManyToOne
    @JoinColumn(name = "USER_UID", nullable = false)
    private User user;

    @Id
    @ManyToOne
    @JoinColumn(name = "LIKED_USER_UID", nullable = false)
    private User likedUser;

}
