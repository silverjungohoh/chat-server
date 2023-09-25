package com.project.chatserver.domain.security.jwt;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

/**
 * access token 유효한지 검증
 * 검증 성공 시 Security Context에 Authentication 객체 저장
 */

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private static final String[] EXCLUDED_URL
		= {"/api/members/sign-up", "/api/members/login", "/h2-console", "/ws/**"};
	private static final String REISSUE_TOKEN_URL = "/api/members/token";

	private final JwtTokenProvider jwtTokenProvider;

	/**
	 * filter 동작 제외
	 */
	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) {
		return Arrays.stream(EXCLUDED_URL).anyMatch(request.getRequestURI()::startsWith);
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
		FilterChain filterChain) throws ServletException, IOException {

		String token;

		if (request.getRequestURI().startsWith(REISSUE_TOKEN_URL)) {
			token = request.getHeader("RTK");
		} else {
			token = jwtTokenProvider.resolveAccessToken(request);
		}

		// token 유효성 검증
		if (!Objects.isNull(token) && jwtTokenProvider.validateToken(token)) {
			Authentication authentication = jwtTokenProvider.getAuthentication(token);
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}

		filterChain.doFilter(request, response);
	}
}