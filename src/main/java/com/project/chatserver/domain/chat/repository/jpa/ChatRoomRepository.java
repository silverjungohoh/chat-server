package com.project.chatserver.domain.chat.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.chatserver.domain.chat.model.entity.ChatRoom;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long>, CustomChatRoomRepository {
}
