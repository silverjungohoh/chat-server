package com.project.chatserver.domain.chat.handler;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.stereotype.Component;

import com.project.chatserver.domain.security.jwt.JwtTokenProvider;

import io.jsonwebtoken.lang.Strings;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * websocket 통해 들어온 요청이 처리되기 전에 실행
 */

@Slf4j
@Component
@Order(Ordered.HIGHEST_PRECEDENCE + 99) // interceptor 우선순위를 spring security 앞으로 설정
@RequiredArgsConstructor
public class ChatPreHandler implements ChannelInterceptor {

	private static final String AUTHORIZATION_HEADER = "Authorization";
	private static final String BEARER_PREFIX = "Bearer ";

	private final JwtTokenProvider jwtTokenProvider;

	@Override
	public Message<?> preSend(Message<?> message, MessageChannel channel) {

		StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);

		// websocket 연결 시 jwt token 검증
		if (StompCommand.CONNECT == accessor.getCommand()) {
			log.info("websocket connect");
			String headerAuth = accessor.getFirstNativeHeader(AUTHORIZATION_HEADER);
			if (Strings.hasText(headerAuth) && headerAuth.startsWith(BEARER_PREFIX)) {
				String accessToken = headerAuth.substring(BEARER_PREFIX.length());
				jwtTokenProvider.validateToken(accessToken);
				log.info("websocket connect success");
			}
		}
		return message;
	}
}