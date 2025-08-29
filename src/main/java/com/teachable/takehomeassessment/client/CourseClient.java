package com.teachable.takehomeassessment.client;

import com.teachable.takehomeassessment.config.FeignClientConfig;
import com.teachable.takehomeassessment.dto.course.ExternalCourseDetailResponse;
import com.teachable.takehomeassessment.dto.course.ExternalCourseSummaryResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
    name = "courseClient",
    url = "${api.url}",
    configuration = FeignClientConfig.class
)
public interface CourseClient {
    @GetMapping("/courses/{id}")
    ExternalCourseDetailResponse getCourseById(@PathVariable("id") Integer id);

    @GetMapping("/courses?is_published=true")
    ExternalCourseSummaryResponse getAllPublishedCourses();
}