package com.teachable.takehomeassessment.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnrolledCourse {
    @JsonProperty("course_id")
    private Integer courseId;
    @JsonProperty("course_name")
    private String courseName;
    @JsonProperty("is_active_enrollment")
    private boolean isActiveEnrollment;
    @JsonProperty("enrolled_at")
    private OffsetDateTime enrolledAt;
    @JsonProperty("completed_at")
    private OffsetDateTime completedAt;
    @JsonProperty("percent_complete")
    private Double percentComplete;
}