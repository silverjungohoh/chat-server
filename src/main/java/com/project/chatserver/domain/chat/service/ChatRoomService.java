package com.project.chatserver.domain.chat.service;

import java.util.List;

import com.project.chatserver.domain.chat.model.dto.ChatRoomInfoResponse;
import com.project.chatserver.domain.chat.model.dto.CreateChatRoomRequest;
import com.project.chatserver.domain.chat.model.dto.CreateChatRoomResponse;
import com.project.chatserver.domain.member.model.entity.Member;

public interface ChatRoomService {

	/**
	 * 채팅방 생성
	 */
	CreateChatRoomResponse createChatRoom(CreateChatRoomRequest request, Member member);

	/**
	 * 채팅방 목록 조회
	 */
	List<ChatRoomInfoResponse> getChatRoomList();
}
