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
@Table(name = "MATCHED_USERS")
public class MatchedUsers {
    @Id
    @GeneratedValue(generator="SEQ_ID_MATCHED_USERS")
    private int id;
    @OneToOne
    @JoinColumn(name = "USER_UID", nullable = false)
    private User user;
    @OneToOne
    @JoinColumn(name = "MATCHED_USER_UID", nullable = false)
    private User matchedUser;
    @Column (name = "MATCHED_DATE")
    private Date matchedDate;
}
