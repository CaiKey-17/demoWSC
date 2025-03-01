package com.example.websocket;

import com.example.websocket.model.UserEntity;
import com.example.websocket.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class StartupRunner implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.count() == 0) {
            UserEntity user1 = new UserEntity(null, "Alice");
            UserEntity user2 = new UserEntity(null, "Bob");
            userRepository.save(user1);
            userRepository.save(user2);
            System.out.println("✅ Created 2 users: Alice & Bob");
        }
    }
}

