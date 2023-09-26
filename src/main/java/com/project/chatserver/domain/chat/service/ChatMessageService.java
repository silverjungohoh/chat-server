package com.project.chatserver.domain.chat.service;

import com.project.chatserver.domain.chat.model.dto.ChatMessageRequest;

public interface ChatMessageService {


	/**
	 * 채팅방 입장
	 */
	void enter(ChatMessageRequest request, Long roomId);

	/**
	 * 채팅방 메세지 전송
	 */
	void send(ChatMessageRequest request, Long roomId);
}
