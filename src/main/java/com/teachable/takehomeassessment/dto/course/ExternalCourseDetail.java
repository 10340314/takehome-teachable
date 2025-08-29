package com.teachable.takehomeassessment.dto.course;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExternalCourseDetail {
    private Integer id;
    private String name;
    private String heading;
    private String description;
    @JsonProperty("is_published")
    private boolean isPublished;
    @JsonProperty("image_url")
    private String imageUrl;

    @JsonProperty("lecture_sections")
    private List<LectureSection> lectureSections;
    @JsonProperty("author_bio")
    private AuthorBio authorBio;
}