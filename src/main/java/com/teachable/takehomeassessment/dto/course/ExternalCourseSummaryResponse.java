package com.teachable.takehomeassessment.dto.course;

import com.teachable.takehomeassessment.dto.Meta;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExternalCourseSummaryResponse {
    private List<ExternalCourseSummary> courses;
    private Meta meta;
}