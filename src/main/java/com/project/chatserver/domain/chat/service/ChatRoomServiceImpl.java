package com.project.chatserver.domain.chat.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.chatserver.domain.chat.model.dto.CreateChatRoomRequest;
import com.project.chatserver.domain.chat.model.dto.CreateChatRoomResponse;
import com.project.chatserver.domain.chat.model.entity.ChatRoom;
import com.project.chatserver.domain.chat.repository.jpa.ChatRoomRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChatRoomServiceImpl implements ChatRoomService {

	private final ChatRoomRepository chatRoomRepository;

	@Override
	@Transactional
	public CreateChatRoomResponse createChatRoom(CreateChatRoomRequest request) {

		ChatRoom chatRoom = ChatRoom.builder()
			.title(request.getTitle())
			.desc(request.getDesc())
			.maxParticipant(request.getMaxParticipant())
			.uniqueKey(UUID.randomUUID().toString())
			.build();

		chatRoomRepository.save(chatRoom);
		return CreateChatRoomResponse.fromEntity(chatRoom);
	}
}
