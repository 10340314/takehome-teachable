package com.teachable.takehomeassessment.dto.course;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Lecture {
    private Integer id;
    private Integer position;
    @JsonProperty("is_published")
    private boolean isPublished;
}