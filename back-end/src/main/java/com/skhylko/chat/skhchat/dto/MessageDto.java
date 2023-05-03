package com.skhylko.chat.skhchat.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class MessageDto {
    private Long id;
    private String text;
    private LocalDateTime sentTime;
}
