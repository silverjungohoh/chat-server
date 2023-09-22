package com.project.chatserver.domain.member.repository.redis;

import com.project.chatserver.domain.member.model.entity.LogoutAccessToken;

import org.springframework.data.repository.CrudRepository;

public interface LogoutAccessTokenRepository extends CrudRepository<LogoutAccessToken, String> {
}
