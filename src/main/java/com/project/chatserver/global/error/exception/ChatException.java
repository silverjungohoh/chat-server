package com.project.chatserver.global.error.exception;

import com.project.chatserver.global.error.type.ChatErrorCode;

import lombok.Getter;

@Getter
public class ChatException extends RuntimeException {

	private final ChatErrorCode errorCode;

	public ChatException(ChatErrorCode errorCode) {
		super(errorCode.getMessage());
		this.errorCode = errorCode;
	}
}
