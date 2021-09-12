package com.glob.ufriends.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "UNIVERSITIES")
public class University {
    @Id
    private String id;
    @Column(name = "NAME")
    private String name;
}
