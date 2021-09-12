package com.glob.ufriends.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@IdClass(UserTagsId.class)
@Table(name = "USER_TAGS")
public class UserTags {

    @Id
    @ManyToOne
    @JoinColumn(name = "USER_UID", nullable = false)
    private User user;

    @Id
    @ManyToOne
    @JoinColumn(name = "TAG_ID", nullable = false)
    private Tag tag;

}
