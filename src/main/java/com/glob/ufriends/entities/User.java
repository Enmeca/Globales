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
@Table(name = "USERS")
public class User {
    @Id
    private String id;
    @Column(name = "CAREER_ID")
    private String careerId;
    @Column(name = "UNIVERSITY_ID")
    private String universityId;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "PASSWORD")
    private String password;
    @Column(name = "NAME")
    private String name;
    @Column(name = "LAST_NAME_1")
    private String lastName1;
    @Column(name = "LAST_NAME_2")
    private String lastName2;
    @Column (name = "DATE_OF_BIRTH")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date dateOfBirth;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "IS_TUTOR")
    private int isTutor;
    @Column(name = "IS_ADMIN")
    private int isAdmin;
}
