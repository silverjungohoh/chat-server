package com.project.chatserver.global.error.handler;

import static com.project.chatserver.global.error.type.CommonErrorCode.*;

import java.util.Objects;

import com.project.chatserver.global.error.exception.MemberException;
import com.project.chatserver.global.error.model.ErrorResponse;

import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MemberException.class)
	public ResponseEntity<ErrorResponse> handleMemberException(MemberException e) {

		ErrorResponse response = ErrorResponse.builder()
			.status(e.getErrorCode().getStatus().value())
			.code(e.getErrorCode().getCode())
			.message(e.getErrorCode().getMessage())
			.build();

		return ResponseEntity.status(e.getErrorCode().getStatus()).body(response);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {

		ErrorResponse response = ErrorResponse.builder()
			.status(INVALID_ARGUMENT.getStatus().value())
			.code(INVALID_ARGUMENT.getCode())
			.message(Objects.requireNonNull(e.getFieldError()).getDefaultMessage())
			.build();

		return ResponseEntity.status(INVALID_ARGUMENT.getStatus()).body(response);
	}

}
