package com.project.chatserver.domain.chat.repository.jpa;

import java.util.List;

import com.project.chatserver.domain.chat.model.dto.ChatRoomInfoResponse;

public interface CustomChatRoomRepository {

	/**
	 * 채팅방 목록 조회
	 */
	List<ChatRoomInfoResponse> findAllChatRoom();
}
