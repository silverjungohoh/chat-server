package com.project.chatserver.global.error.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

import org.springframework.http.HttpStatus;

/**
 * jwt 관련된 예외 CODE = E201 ~ E399
 */

@Getter
@AllArgsConstructor
public enum JwtErrorCode {

	EXPIRED_TOKEN(HttpStatus.UNAUTHORIZED, "E201", "만료된 jwt token 입니다."),
	INCORRECT_SIGNATURE(HttpStatus.UNAUTHORIZED, "E202", "잘못된 jwt 서명입니다."),
	UNSUPPORTED_TOKEN(HttpStatus.UNAUTHORIZED, "E203", "지원되지 않는 jwt token 입니다."),
	INCORRECT_TOKEN(HttpStatus.UNAUTHORIZED, "E204", "잘못된 jwt token 입니다."),
	ALREADY_LOGOUT_TOKEN(HttpStatus.UNAUTHORIZED, "E205", "이미 로그아웃한 회원입니다.");

	private final HttpStatus status;
	private final String code;
	private final String message;
}
