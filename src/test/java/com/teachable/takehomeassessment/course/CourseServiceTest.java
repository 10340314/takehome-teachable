package com.teachable.takehomeassessment.course;

import com.teachable.takehomeassessment.client.CourseClient;
import com.teachable.takehomeassessment.dto.course.ExternalCourseDetail;
import com.teachable.takehomeassessment.dto.course.ExternalCourseDetailResponse;
import com.teachable.takehomeassessment.service.CourseService;
import feign.FeignException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CourseServiceTest {
    @Mock
    private CourseClient courseClient;

    @InjectMocks
    private CourseService courseService;

    @Test
    void shouldReturnCourseWithDetails() {
        Integer courseId = 1;

        ExternalCourseDetail externalCourseDetail = new ExternalCourseDetail(
                courseId,
                "Course test",
                "Heading test",
                "Description test",
                true,
                "imageurl test",
                null,
                null);
        ExternalCourseDetailResponse externalCourseDetailResponse = new ExternalCourseDetailResponse(externalCourseDetail);

        when(courseClient.getCourseById(courseId)).thenReturn(externalCourseDetailResponse);

        ExternalCourseDetailResponse result = courseService.getCourseById(courseId);
        assertEquals(externalCourseDetailResponse, result);
        assertEquals(courseId, result.getCourse().getId());
        assertEquals("Course test", result.getCourse().getName());
        assertEquals("Heading test", result.getCourse().getHeading());
    }

    @Test
    void shouldThrowNotFoundExceptionWhenCourseDoesNotExist() {
        Integer invalidCourseId = 999;

        FeignException.NotFound notFoundException = Mockito.mock(FeignException.NotFound.class);
        when(courseClient.getCourseById(invalidCourseId)).thenThrow(notFoundException);

        assertThrows(FeignException.NotFound.class,
                () -> courseService.getCourseById(invalidCourseId));

        verify(courseClient, times(1)).getCourseById(invalidCourseId);
    }
}