package com.teachable.takehomeassessment.dto.course;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LectureSection {
    private Integer id;
    private String name;
    @JsonProperty("is_published")
    private boolean isPublished;
    private Integer position;
    private List<Lecture> lectures;
}