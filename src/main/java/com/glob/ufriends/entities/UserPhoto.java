package com.glob.ufriends.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "USER_PHOTOS")
public class UserPhoto {
    @Id
    @Column(name = "USER_UID")
    private String id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "USER_UID", nullable = false)
    private User userUid;

    @Column(name = "PROFILE_PIC")
    @Lob
    private byte[] profilePic;

}
