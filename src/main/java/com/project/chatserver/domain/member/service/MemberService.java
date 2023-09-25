package com.project.chatserver.domain.member.service;

import com.project.chatserver.domain.member.model.dto.*;
import com.project.chatserver.domain.member.model.entity.Member;

import java.util.Map;

public interface MemberService {

	/**
	 * 회원 가입
	 */
	Map<String, String> signUp(SignUpRequest request);

	/**
	 * 로그인
	 */
	LoginResponse login(LoginRequest request);

	/**
	 * 로그아웃
	 */
	Map<String, String> logout(LogoutRequest request, String email);

	/**
	 * token 재발급
	 */
	ReissueTokenResponse reissue(String refreshToken, Member member);

	MemberInfoResponse getMemberInfo(Member member);
}
