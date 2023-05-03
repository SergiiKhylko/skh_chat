package com.skhylko.chat.skhchat.service;

import com.skhylko.chat.skhchat.dto.MessageDto;
import com.skhylko.chat.skhchat.dto.MessageRequest;
import com.skhylko.chat.skhchat.entity.Message;
import com.skhylko.chat.skhchat.mapper.MessageMapper;
import com.skhylko.chat.skhchat.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;
    private final MessageMapper messageMapper;

    public List<MessageDto> getMessages() {
        return messageMapper.map(messageRepository.findAllByOrderBySentTime());
    }

    public MessageDto createMessage(MessageRequest message) {
        Message savedMessage = messageRepository.save(messageMapper.map(message));
        return messageMapper.map(savedMessage);
    }
}
