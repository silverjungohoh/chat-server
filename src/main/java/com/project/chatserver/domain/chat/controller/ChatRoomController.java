package com.project.chatserver.domain.chat.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.chatserver.domain.chat.model.dto.ChatRoomInfoResponse;
import com.project.chatserver.domain.chat.model.dto.CreateChatRoomRequest;
import com.project.chatserver.domain.chat.model.dto.CreateChatRoomResponse;
import com.project.chatserver.domain.chat.service.ChatRoomService;
import com.project.chatserver.domain.security.CustomUserDetails;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/chats")
@RequiredArgsConstructor
public class ChatRoomController {

	private final ChatRoomService chatRoomService;

	/**
	 * 채팅방 생성
	 */
	@PostMapping
	public ResponseEntity<?> createChatRoom(@RequestBody @Valid CreateChatRoomRequest request,
		@AuthenticationPrincipal CustomUserDetails userDetails) {

		CreateChatRoomResponse response = chatRoomService.createChatRoom(request, userDetails.getMember());
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	/**
	 * 채팅방 목록 조회
	 */
	@GetMapping
	public ResponseEntity<?> getChatRoomList() {

		List<ChatRoomInfoResponse> response = chatRoomService.getChatRoomList();
		return ResponseEntity.ok(response);
	}
}
