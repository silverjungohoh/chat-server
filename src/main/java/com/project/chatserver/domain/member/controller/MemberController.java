package com.project.chatserver.domain.member.controller;

import com.project.chatserver.domain.member.model.dto.*;
import com.project.chatserver.domain.member.service.MemberService;
import com.project.chatserver.domain.security.CustomUserDetails;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.Map;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {

	private final MemberService memberService;

	/**
	 * 회원 가입
	 */
	@PostMapping("/sign-up")
	public ResponseEntity<?> signUp(@RequestBody @Valid SignUpRequest request) {

		Map<String, String> response = memberService.signUp(request);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	/**
	 * 로그인
	 */
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody @Valid LoginRequest request) {

		LoginResponse response = memberService.login(request);
		return ResponseEntity.ok(response);
	}

	/**
	 * 로그아웃
	 */
	@PostMapping("/logout")
	public ResponseEntity<?> logout(@RequestBody LogoutRequest request,
		@AuthenticationPrincipal CustomUserDetails userDetails) {

		Map<String, String> response = memberService.logout(request, userDetails.getUsername());
		return ResponseEntity.ok(response);
	}

	/**
	 * token 재발급
	 */
	@PostMapping("/token")
	public ResponseEntity<?> reissue(@RequestHeader("RTK") String refreshToken,
		@AuthenticationPrincipal CustomUserDetails userDetails) {

		ReissueTokenResponse response = memberService.reissue(refreshToken, userDetails.getMember());
		return ResponseEntity.ok(response);
	}

	/**
	 * TEST
	 */
	@GetMapping("/test")
	public ResponseEntity<?> test(@AuthenticationPrincipal CustomUserDetails userDetails) {

		return ResponseEntity.ok(userDetails.getUsername());
	}
}
