package com.glob.ufriends.repositories;

import com.glob.ufriends.entities.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepo extends JpaRepository<Tag, String> {
}
