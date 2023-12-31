package com.project.chatserver.domain.chat.model.dto;

import com.project.chatserver.domain.chat.model.entity.ChatRoom;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateChatRoomResponse {

	private String title;

	private Integer maxParticipant;

	private Long chatRoomId;

	public static CreateChatRoomResponse fromEntity(ChatRoom chatRoom) {
		return CreateChatRoomResponse.builder()
			.title(chatRoom.getTitle())
			.maxParticipant(chatRoom.getMaxParticipant())
			.chatRoomId(chatRoom.getId())
			.build();
	}
}
