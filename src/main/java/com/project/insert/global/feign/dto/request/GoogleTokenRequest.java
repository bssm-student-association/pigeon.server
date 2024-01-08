package com.project.insert.global.feign.dto.request;

public record GoogleTokenRequest(
        String clientId,
        String clientSecret,
        String code,
        String grantType,
        String redirectUri
) {}