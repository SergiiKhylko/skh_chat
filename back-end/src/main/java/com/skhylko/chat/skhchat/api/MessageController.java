package com.skhylko.chat.skhchat.api;

import com.skhylko.chat.skhchat.dto.MessageDto;
import com.skhylko.chat.skhchat.dto.MessageRequest;
import com.skhylko.chat.skhchat.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1/messages")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @GetMapping
    public ResponseEntity<List<MessageDto>> getAll() {
        return ResponseEntity.ok(messageService.getMessages());
    }

    @PostMapping
    public ResponseEntity<MessageDto> sentMessage(@RequestBody MessageRequest message) {
        MessageDto savedMessage = messageService.createMessage(message);
        return ResponseEntity.created(URI.create("")).body(savedMessage);
    }
}
