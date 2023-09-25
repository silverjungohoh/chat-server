package com.project.chatserver.domain.chat.model.dto;

import com.project.chatserver.domain.chat.model.type.MessageType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessageRequest {

    private MessageType type;

    private Long chatRoomId;

    private String sender; // nickname

    private String content;
}
