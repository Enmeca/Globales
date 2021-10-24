package com.glob.ufriends.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPhotoHelper {
    String userId;
    String base64Photo;
}
