package com.teachable.takehomeassessment.enrollment;

import com.teachable.takehomeassessment.client.EnrollmentClient;
import com.teachable.takehomeassessment.dto.enrollment.Enrollment;
import com.teachable.takehomeassessment.dto.enrollment.ExternalEnrollmentResponse;
import com.teachable.takehomeassessment.service.EnrollmentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.OffsetDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EnrollmentServiceTest {
    @Mock
    private EnrollmentClient enrollmentClient;
    @InjectMocks
    private EnrollmentService enrollmentService;

    @Test
    void shouldReturnEnrollmentsByCourseId() {
        Integer courseId = 1;

        List<Enrollment> enrollments = List.of(
                new Enrollment(1, OffsetDateTime.now(), null, null, 0)
        );
        ExternalEnrollmentResponse externalEnrollmentResponse = new ExternalEnrollmentResponse(enrollments, null);

        when(enrollmentClient.getEnrollmentsByCourseId(courseId)).thenReturn(externalEnrollmentResponse);

        ExternalEnrollmentResponse result = enrollmentService.getEnrollmentsByCourseId(courseId);
        assertEquals(externalEnrollmentResponse, result);
        assertEquals(1, result.getEnrollments().get(0).getUserId());
        assertEquals(1, result.getEnrollments().size());
    }
}
