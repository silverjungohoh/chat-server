package com.project.chatserver.domain.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.chatserver.global.error.model.ErrorResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static com.project.chatserver.global.error.type.MemberErrorCode.FAIL_TO_AUTHENTICATION;
import static javax.servlet.http.HttpServletResponse.SC_UNAUTHORIZED;

/**
 * AuthenticationException 처리
 */

@Slf4j
@Component
@RequiredArgsConstructor
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

	private final ObjectMapper objectMapper;

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
		AuthenticationException authException) throws IOException {
		log.error(authException.getMessage());

		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		response.setCharacterEncoding("UTF-8");
		response.setStatus(SC_UNAUTHORIZED);

		ErrorResponse errorResponse = ErrorResponse.builder()
			.status(FAIL_TO_AUTHENTICATION.getStatus().value())
			.code(FAIL_TO_AUTHENTICATION.getCode())
			.message(FAIL_TO_AUTHENTICATION.getMessage())
			.build();

		response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
	}
}
