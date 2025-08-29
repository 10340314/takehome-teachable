package com.teachable.takehomeassessment.dto.course;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExternalCourseSummary {
    private Integer id;
    private String name;
    private String heading;
    private String description;
    @JsonProperty("is_published")
    private boolean isPublished;
    private String imageUrl;
}