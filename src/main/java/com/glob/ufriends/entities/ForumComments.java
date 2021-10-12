package com.glob.ufriends.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "FORUM_COMMENTS")
public class ForumComments {
    @Id
    @GeneratedValue(generator="SEQ_ID_FORUM_COMMENTS")
    private int id;
    @OneToOne
    @JoinColumn(name = "FORUM_ID", nullable = false)
    private ForumTopic forumId;
    @OneToOne
    @JoinColumn(name = "AUTHOR_ID", nullable = false)
    private User authorId;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column (name = "CREATION_DATE")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date creationDate;
    @Column (name = "IS_ANON")
    private int isAnon;
}
