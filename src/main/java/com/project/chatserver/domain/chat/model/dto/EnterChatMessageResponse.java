package com.project.chatserver.domain.chat.model.dto;

import java.time.LocalDateTime;

import com.project.chatserver.domain.chat.model.type.MessageType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EnterChatMessageResponse {

    private MessageType type;

    private String content;

    private LocalDateTime createdAt;
}
