package com.teachable.takehomeassessment.client;

import com.teachable.takehomeassessment.config.FeignClientConfig;
import com.teachable.takehomeassessment.dto.enrollment.ExternalEnrollmentResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "enrollmentClient",
        url = "${api.url}",
        configuration = FeignClientConfig.class
)
public interface EnrollmentClient {
    @GetMapping("/courses/{courseId}/enrollments")
    ExternalEnrollmentResponse getEnrollmentsByCourseId(@PathVariable Integer courseId);
}