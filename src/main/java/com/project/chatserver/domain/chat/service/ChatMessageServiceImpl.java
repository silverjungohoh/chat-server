package com.project.chatserver.domain.chat.service;

import static com.project.chatserver.global.error.type.ChatErrorCode.*;
import static com.project.chatserver.global.error.type.MemberErrorCode.*;

import java.time.LocalDateTime;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import com.project.chatserver.domain.chat.model.dto.ChatMessageRequest;
import com.project.chatserver.domain.chat.model.dto.EnterChatMessageResponse;
import com.project.chatserver.domain.chat.model.entity.ChatRoom;
import com.project.chatserver.domain.chat.model.entity.ChatRoomMember;
import com.project.chatserver.domain.chat.model.type.MessageType;
import com.project.chatserver.domain.chat.repository.jpa.ChatRoomMemberRepository;
import com.project.chatserver.domain.chat.repository.jpa.ChatRoomRepository;
import com.project.chatserver.domain.member.model.entity.Member;
import com.project.chatserver.domain.member.repository.jpa.MemberRepository;
import com.project.chatserver.global.error.exception.ChatException;
import com.project.chatserver.global.error.exception.MemberException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatMessageServiceImpl implements ChatMessageService {

	private final static String CHAT_EXCHANGE_NAME = "chat.exchange";

	private final ChatRoomRepository chatRoomRepository;
	private final MemberRepository memberRepository;
	private final ChatRoomMemberRepository chatRoomMemberRepository;
	private final RabbitTemplate rabbitTemplate;

	@Override
	public void enter(ChatMessageRequest request, Long roomId) {

		// 채팅방이 존재하지 않는 경우
		ChatRoom chatRoom = chatRoomRepository.findById(request.getChatRoomId())
			.orElseThrow(() -> new ChatException(CHAT_ROOM_NOT_FOUND));

		Member member = memberRepository.findByNickname(request.getSender())
			.orElseThrow(() -> new MemberException(MEMBER_NOT_FOUND));

		// 최대 인원 수 초과 X
		if (chatRoomMemberRepository.countByChatRoomAndMember(chatRoom, member) >= chatRoom.getMaxParticipant()) {
			throw new ChatException(CHAT_ROOM_EXCEEDED_MAX);
		}

		// 해당 채팅방 멤버가 아닌 경우 추가 >> 방장은 이미 포함되어 있음
		if (!chatRoomMemberRepository.existsByChatRoomAndMember(chatRoom, member)) {
			ChatRoomMember chatRoomMember = ChatRoomMember.builder()
				.chatRoom(chatRoom)
				.member(member)
				.isOwner(false)
				.build();

			chatRoomMemberRepository.save(chatRoomMember);
			log.info("save member in chat room {}", roomId);
		}

		EnterChatMessageResponse response = EnterChatMessageResponse.builder()
			.type(MessageType.ENTER)
			.content(member.getNickname() + "님이 접속하였습니다.")
			.createdAt(LocalDateTime.now())
			.build();

		rabbitTemplate.convertAndSend(CHAT_EXCHANGE_NAME, "room." + roomId, response);
	}
}
