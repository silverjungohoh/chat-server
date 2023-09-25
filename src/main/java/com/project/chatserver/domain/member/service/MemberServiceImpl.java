package com.project.chatserver.domain.member.service;

import static com.project.chatserver.domain.member.model.type.MemberRole.*;
import static com.project.chatserver.global.error.type.MemberErrorCode.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.chatserver.domain.member.model.dto.LoginRequest;
import com.project.chatserver.domain.member.model.dto.LoginResponse;
import com.project.chatserver.domain.member.model.dto.LogoutRequest;
import com.project.chatserver.domain.member.model.dto.MemberInfoResponse;
import com.project.chatserver.domain.member.model.dto.ReissueTokenResponse;
import com.project.chatserver.domain.member.model.dto.SignUpRequest;
import com.project.chatserver.domain.member.model.entity.LogoutAccessToken;
import com.project.chatserver.domain.member.model.entity.Member;
import com.project.chatserver.domain.member.model.entity.RefreshToken;
import com.project.chatserver.domain.member.repository.jpa.MemberRepository;
import com.project.chatserver.domain.member.repository.redis.LogoutAccessTokenRepository;
import com.project.chatserver.domain.member.repository.redis.RefreshTokenRepository;
import com.project.chatserver.domain.security.CustomUserDetails;
import com.project.chatserver.domain.security.jwt.JwtTokenProvider;
import com.project.chatserver.global.error.exception.MemberException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

	private final MemberRepository memberRepository;
	private final PasswordEncoder passwordEncoder;
	private final AuthenticationManagerBuilder authenticationManagerBuilder;
	private final JwtTokenProvider jwtTokenProvider;
	private final RefreshTokenRepository refreshTokenRepository;
	private final LogoutAccessTokenRepository logoutAccessTokenRepository;

	@Override
	public Map<String, String> signUp(SignUpRequest request) {

		if (memberRepository.existsByEmail(request.getEmail())) {
			throw new MemberException(DUPLICATED_EMAIL);
		}

		if (memberRepository.existsByNickname(request.getNickname())) {
			throw new MemberException(DUPLICATED_NICKNAME);
		}

		Member member = Member.builder()
			.email(request.getEmail())
			.nickname(request.getNickname())
			.password(passwordEncoder.encode(request.getPassword()))
			.role(USER)
			.build();

		memberRepository.save(member);

		return getMessage("회원 가입 성공");
	}

	@Override
	public LoginResponse login(LoginRequest request) {

		UsernamePasswordAuthenticationToken authenticationToken
			= new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());

		Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

		SecurityContextHolder.getContext().setAuthentication(authentication);

		CustomUserDetails userDetails = (CustomUserDetails)authentication.getPrincipal();

		String accessToken
			= jwtTokenProvider.generateAccessToken(userDetails.getUsername(), userDetails.getRole().getValue());

		String refreshToken
			= jwtTokenProvider.generateRefreshToken(userDetails.getUsername());

		return LoginResponse.builder()
			.accessToken(accessToken)
			.refreshToken(refreshToken)
			.build();
	}

	@Override
	public Map<String, String> logout(LogoutRequest request, String email) {

		String accessToken = request.getAccessToken();
		Long remainingTime = jwtTokenProvider.getRemainingTime(accessToken);

		LogoutAccessToken logoutAccessToken = LogoutAccessToken.builder()
			.id(accessToken)
			.email(email)
			.expiration(remainingTime)
			.build();

		logoutAccessTokenRepository.save(logoutAccessToken);

		// redis refresh token 삭제
		refreshTokenRepository.deleteById(email);

		return getMessage("로그아웃 성공");
	}

	@Override
	public ReissueTokenResponse reissue(String refreshToken, Member member) {

		RefreshToken refreshTokenInRedis = refreshTokenRepository.findById(member.getEmail())
			.orElseThrow(() -> new MemberException(FAIL_TO_REISSUE_TOKEN));

		if (!Objects.equals(refreshTokenInRedis.getRefreshToken(), refreshToken)) {
			throw new MemberException(FAIL_TO_REISSUE_TOKEN);
		}

		String newAccessToken
			= jwtTokenProvider.generateAccessToken(member.getEmail(), member.getRole().getValue());

		String newRefreshToken
			= jwtTokenProvider.generateRefreshToken(member.getEmail());

		return ReissueTokenResponse.builder()
			.accessToken(newAccessToken)
			.refreshToken(newRefreshToken)
			.build();
	}

	@Override
	public MemberInfoResponse getMemberInfo(Member member) {
		return MemberInfoResponse.builder()
			.nickname(member.getNickname())
			.build();
	}

	private static Map<String, String> getMessage(String message) {
		Map<String, String> result = new HashMap<>();
		result.put("result", message);
		return result;
	}
}
