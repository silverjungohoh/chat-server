package com.project.chatserver.domain.chat.service;

import com.project.chatserver.domain.chat.model.dto.ChatMessageRequest;

public interface ChatMessageService {


	/**
	 * 채팅방 입장
	 */
	void enter(ChatMessageRequest request, Long roomId);
}
