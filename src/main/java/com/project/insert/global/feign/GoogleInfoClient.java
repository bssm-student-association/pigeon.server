package com.project.insert.global.feign;

import com.project.insert.global.feign.dto.response.GoogleInfoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "GoogleInfoClient", url = "https://www.googleapis.com")
public interface GoogleInfoClient {

    @GetMapping("/oauth2/v1/userinfo?access_token={accessToken}")
    GoogleInfoResponse getUserInfo(@PathVariable("accessToken") String token);
}