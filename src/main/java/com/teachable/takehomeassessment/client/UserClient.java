package com.teachable.takehomeassessment.client;

import com.teachable.takehomeassessment.config.FeignClientConfig;
import com.teachable.takehomeassessment.dto.user.ExternalUserDetail;
import com.teachable.takehomeassessment.dto.user.ExternalUserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(
        name = "userClient",
        url = "${api.url}",
        configuration = FeignClientConfig.class
)
public interface UserClient {
    @GetMapping("/users/{id}")
    ExternalUserDetail getUserById(@PathVariable Integer id);

    @GetMapping("/users")
    ExternalUserResponse getAllUsers(@RequestParam Map<String, Object> filter);
}