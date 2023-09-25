package com.project.chatserver.domain.chat.controller;

import javax.validation.Valid;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.chatserver.domain.chat.model.dto.ChatMessageRequest;
import com.project.chatserver.domain.chat.model.dto.ChatMessageResponse;
import com.project.chatserver.domain.chat.service.ChatMessageService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ChatMessageController {

    private final static String CHAT_QUEUE_NAME = "chat.queue";

    private final ChatMessageService chatMessageService;

    /**
     * 채팅방 입장
     */
    @MessageMapping("chat.enter.{roomId}")
    public void enter(@Valid ChatMessageRequest request, @DestinationVariable Long roomId) {
        log.info("{} enter roomId {}", request.getSender(), roomId);
        chatMessageService.enter(request, roomId);
    }


    @RabbitListener(queues = CHAT_QUEUE_NAME)
    public void receive(ChatMessageResponse response) {
        log.info("response.getContent() = {}", response.getContent());
    }
}
