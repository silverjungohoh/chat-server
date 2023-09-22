package com.project.chatserver.global.error.type;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CommonErrorCode {

	INVALID_ARGUMENT(HttpStatus.BAD_REQUEST, "E000", "유효성 검증 실패");

	private final HttpStatus status;
	private final String code;
	private final String message;
}
