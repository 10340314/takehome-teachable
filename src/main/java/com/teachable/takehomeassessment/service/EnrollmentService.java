package com.teachable.takehomeassessment.service;

import com.teachable.takehomeassessment.client.EnrollmentClient;
import com.teachable.takehomeassessment.dto.enrollment.ExternalEnrollmentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnrollmentService {
    @Autowired
    private EnrollmentClient enrollmentClient;

    public ExternalEnrollmentResponse getEnrollmentsByCourseId(Integer courseId) {
        return enrollmentClient.getEnrollmentsByCourseId(courseId);
    }
}