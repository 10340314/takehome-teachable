package com.teachable.takehomeassessment.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;

public class ApiKeyRequestInterceptor implements RequestInterceptor {
    private final String apiKey;

    public ApiKeyRequestInterceptor(String apiKey) {
        this.apiKey = apiKey;
    }

    @Override
    public void apply(RequestTemplate requestTemplate) {
        requestTemplate.header("apiKey", apiKey);
    }
}
