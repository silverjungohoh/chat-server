package com.project.chatserver.domain.chat.repository.jpa.impl;

import static com.project.chatserver.domain.chat.model.entity.QChatRoom.*;
import static com.project.chatserver.domain.chat.model.entity.QChatRoomMember.*;
import static com.querydsl.core.types.ExpressionUtils.*;
import static com.querydsl.core.types.Projections.*;
import static com.querydsl.jpa.JPAExpressions.*;

import java.util.List;

import com.project.chatserver.domain.chat.model.dto.ChatRoomInfoResponse;
import com.project.chatserver.domain.chat.repository.jpa.CustomChatRoomRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CustomChatRoomRepositoryImpl implements CustomChatRoomRepository {

	private final JPAQueryFactory queryFactory;

	@Override
	public List<ChatRoomInfoResponse> findAllChatRoom() {

		return queryFactory.select(fields(ChatRoomInfoResponse.class,
				chatRoom.id.as("chatRoomId"),
				chatRoom.title.as("title"),
				chatRoom.desc.as("desc"),
				as(select(chatRoomMember.id.count())
						.from(chatRoomMember)
						.where(chatRoomMember.chatRoom.id.eq(chatRoom.id)),
					"chatRoomMemberCnt"),
				chatRoom.maxParticipant.as("maxParticipant")
			))
			.from(chatRoom)
			.orderBy(chatRoom.createdAt.desc())
			.fetch();
	}
}
