package com.skhylko.chat.skhchat.mapper;

import com.skhylko.chat.skhchat.dto.MessageDto;
import com.skhylko.chat.skhchat.dto.MessageRequest;
import com.skhylko.chat.skhchat.entity.Message;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Component
public class MessageMapper {
    public MessageDto map(Message message) {
        return MessageDto.builder()
                .id(message.getId())
                .text(message.getText())
                .sentTime(message.getSentTime())
                .build();
    }

    public Message map(MessageRequest message) {
        return Message.builder()
                .text(message.getText())
                .sentTime(LocalDateTime.now())
                .build();
    }

    public List<MessageDto> map(Collection<Message> messages) {
        return messages.stream().map(this::map).toList();
    }
}
