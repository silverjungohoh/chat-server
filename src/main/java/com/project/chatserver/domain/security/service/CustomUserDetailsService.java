package com.project.chatserver.domain.security.service;

import com.project.chatserver.domain.member.model.entity.Member;
import com.project.chatserver.domain.member.repository.jpa.MemberRepository;
import com.project.chatserver.domain.security.CustomUserDetails;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

	private final MemberRepository memberRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		log.info("loadUserByUsername method call");

		Member member = memberRepository.findByEmail(username)
			.orElseThrow(() -> new UsernameNotFoundException("존재하지 않는 회원입니다."));

		return new CustomUserDetails(member);
	}
}
