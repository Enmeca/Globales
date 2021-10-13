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
@IdClass(CommentReportsId.class)
@Table(name = "COMMENT_REPORTS")
public class CommentReports {
    
    @Id
    @ManyToOne
    @JoinColumn(name = "COMMENT_ID", nullable = false)
    private ForumComments comment;

    @Id
    @ManyToOne
    @JoinColumn(name = "USER_UID", nullable = false)
    private User user;
    
    @Column(name = "MOTIVE")
    private String motive;
    
    @Column (name = "CREATION_DATE")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date creationDate;
}
