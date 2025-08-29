package com.teachable.takehomeassessment.dto.enrollment;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Enrollment {
    @JsonProperty("user_id")
    private Integer userId;
    @JsonProperty("enrolled_at")
    private OffsetDateTime enrolledAt;
    @JsonProperty("expires_at")
    private OffsetDateTime expiresAt;
    @JsonProperty("completed_at")
    private OffsetDateTime completedAt;
    @JsonProperty("percent_complete")
    private Integer percentComplete;
}