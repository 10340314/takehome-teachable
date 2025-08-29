package com.teachable.takehomeassessment.service;

import com.teachable.takehomeassessment.client.CourseClient;
import com.teachable.takehomeassessment.dto.course.ExternalCourseDetailResponse;
import com.teachable.takehomeassessment.dto.course.ExternalCourseSummaryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseService {
    @Autowired
    private CourseClient courseClient;

    public ExternalCourseDetailResponse getCourseById(Integer id) {
        return courseClient.getCourseById(id);
    }

    public ExternalCourseSummaryResponse getAllPublishedCourses() {
        return courseClient.getAllPublishedCourses();
    }
}