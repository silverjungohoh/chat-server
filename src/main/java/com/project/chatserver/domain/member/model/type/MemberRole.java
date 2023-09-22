package com.project.chatserver.domain.member.model.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MemberRole {

	USER("ROLE_USER");

	private final String value;
}
