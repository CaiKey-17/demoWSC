package com.example.websocket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.websocket.model.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
}

