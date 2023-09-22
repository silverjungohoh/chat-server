package com.project.chatserver.domain.chat.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.chatserver.domain.chat.model.dto.CreateChatRoomRequest;
import com.project.chatserver.domain.chat.model.dto.CreateChatRoomResponse;
import com.project.chatserver.domain.chat.service.ChatRoomService;

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
	public ResponseEntity<?> createChatRoom(@RequestBody @Valid CreateChatRoomRequest request) {

		CreateChatRoomResponse response = chatRoomService.createChatRoom(request);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
}
