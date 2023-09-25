package com.project.chatserver.domain.chat.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.chatserver.domain.chat.model.entity.ChatRoom;
import com.project.chatserver.domain.chat.model.entity.ChatRoomMember;
import com.project.chatserver.domain.member.model.entity.Member;

public interface ChatRoomMemberRepository extends JpaRepository<ChatRoomMember, Long> {

	boolean existsByChatRoomAndMember(ChatRoom chatRoom, Member member);

	Long countByChatRoomAndMember(ChatRoom chatRoom, Member member);
}
