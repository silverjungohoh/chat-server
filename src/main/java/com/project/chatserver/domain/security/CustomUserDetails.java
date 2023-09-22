package com.project.chatserver.domain.security;

import com.project.chatserver.domain.member.model.entity.Member;
import com.project.chatserver.domain.member.model.type.MemberRole;

import lombok.Getter;
import lombok.NoArgsConstructor;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Getter
@NoArgsConstructor
public class CustomUserDetails implements UserDetails {

	private String username;

	private String password;

	private Member member;

	private MemberRole role;

	public CustomUserDetails(Member member) {
		this.username = member.getEmail();
		this.password = member.getPassword();
		this.member = member;
		this.role = member.getRole();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.singleton(new SimpleGrantedAuthority(role.getValue()));
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
