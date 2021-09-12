package com.glob.ufriends.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "FORUM_TOPICS")
public class ForumTopic {
    @Id
    private int id;
    @OneToOne
    @JoinColumn(name = "AUTHOR_ID", nullable = false)
    private User authorId;
    @Column(name = "TITLE")
    private String title;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column (name = "CREATION_DATE")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date creationDate;
    @Column (name = "IS_ANON")
    private int isAnon;
}
