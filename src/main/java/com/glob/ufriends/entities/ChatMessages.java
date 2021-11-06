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
@Table(name = "CHAT_MESSAGES")
public class ChatMessages {
    @Id
    @GeneratedValue(generator="SEQ_ID_CHAT_MESSAGES")
    private int id;
    @OneToOne
    @JoinColumn(name = "CHAT_UID", nullable = false)
    private MatchedUsers chatUid;
    @OneToOne
    @JoinColumn(name = "USER_UID", nullable = false)
    private User userUid; // User that sent the message
    @Column(name = "MESSAGE")
    private String message;
    @Column (name = "DATE_SENT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateSent;
    @Column (name = "WAS_READ")
    private int wasRead;
}
