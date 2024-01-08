package com.project.insert.global.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties("oauth.google")
public class GoogleAuthProperties {

    private String clientId;
    private String clientSecret;
    private String grantType;
    private String redirectUri;
}