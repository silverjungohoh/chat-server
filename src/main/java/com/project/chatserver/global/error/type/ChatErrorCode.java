package com.project.chatserver.global.error.type;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ChatErrorCode {

	;

	private final HttpStatus status;
	private final String code;
	private final String message;
}
