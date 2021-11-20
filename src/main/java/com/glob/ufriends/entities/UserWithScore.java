package com.glob.ufriends.entities;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserWithScore {
    User user;
    List<String> tags;
    int score;
}
