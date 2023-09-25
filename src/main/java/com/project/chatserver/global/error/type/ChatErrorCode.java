package com.project.chatserver.global.error.type;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ChatErrorCode {

	CHAT_ROOM_NOT_FOUND(HttpStatus.NOT_FOUND, "E301", "존재하지 않는 채팅방입니다."),
	CHAT_ROOM_EXCEEDED_MAX(HttpStatus.BAD_REQUEST, "E302", "채팅방 최대 인원 수를 초과할 수 없습니다.");

	private final HttpStatus status;
	private final String code;
	private final String message;
}
