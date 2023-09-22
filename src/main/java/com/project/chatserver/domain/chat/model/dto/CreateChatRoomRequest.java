package com.project.chatserver.domain.chat.model.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateChatRoomRequest {

	@NotBlank(message = "제목을 입력해주세요.")
	private String title;

	@NotBlank(message = "설명을 입력해주세요.")
	private String desc;

	@Min(value = 2, message = "채팅방 최소 인원 수를 2명입니다.")
	@Max(value = 5, message = "채팅방 최대 인원 수는 5명입니다.")
	private Integer maxParticipant;
}
