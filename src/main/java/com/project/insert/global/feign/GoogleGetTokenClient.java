package com.project.insert.global.feign;

import com.project.insert.global.feign.dto.request.GoogleTokenRequest;
import com.project.insert.global.feign.dto.response.GoogleTokenResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "GoogleGetTokenClient", url = "https://oauth2.googleapis.com/token")
public interface GoogleGetTokenClient {

    @PostMapping
    GoogleTokenResponse getToken(GoogleTokenRequest request);
}