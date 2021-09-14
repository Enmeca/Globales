package com.glob.ufriends.repositories;

import com.glob.ufriends.entities.ForumTopic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ForumTopicRepo extends JpaRepository<ForumTopic, Integer> {
}
