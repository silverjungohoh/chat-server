package com.project.chatserver.domain.member.repository.redis;

import com.project.chatserver.domain.member.model.entity.RefreshToken;

import org.springframework.data.repository.CrudRepository;

public interface RefreshTokenRepository extends CrudRepository<RefreshToken, String> {
}
