package com.teachable.takehomeassessment.dto.enrollment;

import com.teachable.takehomeassessment.dto.Meta;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExternalEnrollmentResponse {
    private List<Enrollment> enrollments;
    private Meta meta;
}