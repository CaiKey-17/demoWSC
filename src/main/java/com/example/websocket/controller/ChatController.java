package com.example.websocket.controller;

import com.example.websocket.model.ChatMessageEntity;
import com.example.websocket.repository.ChatMessageRepository;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RestController
@RequestMapping("/api/chat")
public class ChatController {

    private final ChatMessageRepository chatMessageRepository;

    public ChatController(ChatMessageRepository chatMessageRepository) {
        this.chatMessageRepository = chatMessageRepository;
    }

    @MessageMapping("/sendMessage")
    @SendTo("/topic/chat")
    public ChatMessageEntity sendMessage(@Payload ChatMessageEntity message) {
        message.setTimestamp(LocalDateTime.now());
        chatMessageRepository.save(message); // Lưu vào database
        return message;
    }

    // API để lấy tin nhắn cũ giữa 2 user
    @GetMapping("/messages/{userId}/{otherUserId}")
    public List<ChatMessageEntity> getChatHistory(
            @PathVariable Long userId,
            @PathVariable Long otherUserId) {
        return chatMessageRepository.findBySenderReceiver(userId, otherUserId);
    }
}


