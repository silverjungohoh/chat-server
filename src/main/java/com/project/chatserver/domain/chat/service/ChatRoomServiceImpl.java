package com.project.chatserver.domain.chat.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.chatserver.domain.chat.model.dto.ChatRoomInfoResponse;
import com.project.chatserver.domain.chat.model.dto.CreateChatRoomRequest;
import com.project.chatserver.domain.chat.model.dto.CreateChatRoomResponse;
import com.project.chatserver.domain.chat.model.entity.ChatRoom;
import com.project.chatserver.domain.chat.model.entity.ChatRoomMember;
import com.project.chatserver.domain.chat.repository.jpa.ChatRoomMemberRepository;
import com.project.chatserver.domain.chat.repository.jpa.ChatRoomRepository;
import com.project.chatserver.domain.member.model.entity.Member;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChatRoomServiceImpl implements ChatRoomService {

	private final ChatRoomRepository chatRoomRepository;
	private final ChatRoomMemberRepository chatRoomMemberRepository;

	@Override
	@Transactional
	public CreateChatRoomResponse createChatRoom(CreateChatRoomRequest request, Member member) {

		ChatRoom chatRoom = ChatRoom.builder()
			.title(request.getTitle())
			.desc(request.getDesc())
			.maxParticipant(request.getMaxParticipant())
			.uniqueKey(UUID.randomUUID().toString())
			.build();

		chatRoomRepository.save(chatRoom);

		ChatRoomMember chatRoomMember = ChatRoomMember.builder()
			.member(member)
			.chatRoom(chatRoom)
			.isOwner(true) // 생성한 사람 = 채팅방 방장
			.build();

		chatRoomMemberRepository.save(chatRoomMember);

		return CreateChatRoomResponse.fromEntity(chatRoom);
	}

	@Override
	public List<ChatRoomInfoResponse> getChatRoomList() {
		return chatRoomRepository.findAllChatRoom();
	}
}
