package com.project.chatserver.domain.chat.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatRoomInfoResponse {

	private Long chatRoomId;

	private String title;

	private String desc;

	private Long chatRoomMemberCnt; // 채팅방 인원 수

	private Integer maxParticipant; // 채팅방 최대 인원 수
}
