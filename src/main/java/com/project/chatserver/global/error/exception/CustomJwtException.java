package com.project.chatserver.global.error.exception;

import com.project.chatserver.global.error.type.JwtErrorCode;

import lombok.Getter;

@Getter
public class CustomJwtException extends RuntimeException {

	private final JwtErrorCode errorCode;

	public CustomJwtException(JwtErrorCode errorCode) {
		super(errorCode.getMessage());
		this.errorCode = errorCode;
	}
}
