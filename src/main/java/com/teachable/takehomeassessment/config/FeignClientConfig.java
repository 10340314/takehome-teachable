package com.teachable.takehomeassessment.config;

import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

public class FeignClientConfig {
    @Value("${api.key}")
    private String apiKey;

    @Bean
    public RequestInterceptor apiKeyRequestInterceptor() {
        return new ApiKeyRequestInterceptor(apiKey);
    }
}