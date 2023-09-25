package com.project.chatserver.global.error.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MemberErrorCode {

	DUPLICATED_EMAIL(HttpStatus.CONFLICT, "E101", "이미 존재하는 이메일입니다."),
	DUPLICATED_NICKNAME(HttpStatus.CONFLICT, "E102", "이미 존재하는 닉네임입니다."),
	FAIL_TO_AUTHENTICATION(HttpStatus.UNAUTHORIZED, "E103", "사용자 인증에 실패하였습니다."),
	FAIL_TO_REISSUE_TOKEN(HttpStatus.UNAUTHORIZED, "E104", "token 재발급이 불가능합니다."),
	MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "E105", "존재하지 않는 회원입니다.");

	private final HttpStatus status;
	private final String code;
	private final String message;
}
