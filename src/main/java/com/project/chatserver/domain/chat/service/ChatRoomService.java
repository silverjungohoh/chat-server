package com.project.chatserver.domain.chat.service;

import com.project.chatserver.domain.chat.model.dto.CreateChatRoomRequest;
import com.project.chatserver.domain.chat.model.dto.CreateChatRoomResponse;
import com.project.chatserver.domain.member.model.entity.Member;

public interface ChatRoomService {

	/**
	 * 채팅방 생성
	 */
	CreateChatRoomResponse createChatRoom(CreateChatRoomRequest request, Member member);
}
