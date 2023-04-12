package com.cloud.project.controller;

import com.cloud.project.common.ErrorCode;
import com.cloud.project.common.ResponseEntity;
import com.cloud.project.entity.Article;
import com.cloud.project.entity.Chat;
import com.cloud.project.entity.Message;
import com.cloud.project.entity.User;
import com.cloud.project.service.ChatService;
import com.cloud.project.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chat")
public class ChatController {
    @Autowired
    private ChatService chatService;

    @GetMapping("/{id}")
    public ResponseEntity<Chat> getChatById(@PathVariable Integer id) {
        Chat chat = chatService.findByChatId(id);
        if (chat == null){
            return ResponseEntity.error(ErrorCode.TargetNotExists,"Id invalid",null);
        }
        return ResponseEntity.resultBuild(1, chat);
    }

    @GetMapping("/users/{a_id}&{b_id}")
    public ResponseEntity<Chat> getChatByUsers(@PathVariable Integer a_id,@PathVariable Integer b_id) {
        Chat chat = chatService.findByUsers(a_id, b_id);
        if (chat == null){
            return ResponseEntity.error(ErrorCode.TargetNotExists,"No Chat between these two Users",null);
        }
        return ResponseEntity.resultBuild(1, chat);
    }

    @GetMapping("/target/{id}")
    public ResponseEntity<List<User>> relatedUsers(@PathVariable Integer id) {
        List<User> users = chatService.relatedId(id);
        if (users == null){
            return ResponseEntity.error(ErrorCode.TargetNotExists,"No Chats",null);
        }
        return ResponseEntity.resultBuild(users.size(), users);
    }

    @PostMapping("/message")
    public ResponseEntity<Chat> newMessage(@RequestBody Message message) {
        Chat chat = chatService.newMessage(message.getId(),message.getSender(),message.getMessage());
        if (chat == null){
            return ResponseEntity.error(ErrorCode.SendFailed,"Send Message Failed",null);
        }
        return ResponseEntity.ok(chat);
    }
}
