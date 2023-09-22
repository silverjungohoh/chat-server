package com.project.chatserver.domain.member.model.entity;

import com.project.chatserver.domain.member.model.type.MemberRole;
import com.project.chatserver.global.entity.BaseTimeEntity;

import lombok.*;

import javax.persistence.*;

@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseTimeEntity {

	@Id
	@Column(name = "member_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true)
	private String email;

	private String password;

	@Column(unique = true)
	private String nickname;

	@Enumerated(EnumType.STRING)
	private MemberRole role;
}
