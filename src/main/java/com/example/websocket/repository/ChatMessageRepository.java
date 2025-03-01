package com.example.websocket.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.example.websocket.model.ChatMessageEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessageEntity, Long> {

    @Query("SELECT m FROM ChatMessageEntity m WHERE " +
            "(m.senderId = :userId AND m.receiverId = :otherUserId) OR " +
            "(m.senderId = :otherUserId AND m.receiverId = :userId) " +
            "ORDER BY m.timestamp ASC")
    List<ChatMessageEntity> findBySenderReceiver(Long userId, Long otherUserId);
}

