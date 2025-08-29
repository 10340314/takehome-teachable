package com.teachable.takehomeassessment.report;

import com.teachable.takehomeassessment.Cache;
import com.teachable.takehomeassessment.domain.CourseReport;
import com.teachable.takehomeassessment.domain.Student;
import com.teachable.takehomeassessment.dto.course.ExternalCourseDetail;
import com.teachable.takehomeassessment.dto.course.ExternalCourseDetailResponse;
import com.teachable.takehomeassessment.dto.enrollment.Enrollment;
import com.teachable.takehomeassessment.dto.enrollment.ExternalEnrollmentResponse;
import com.teachable.takehomeassessment.dto.user.ExternalUserDetail;
import com.teachable.takehomeassessment.service.CourseService;
import com.teachable.takehomeassessment.service.EnrollmentService;
import com.teachable.takehomeassessment.service.ReportService;
import com.teachable.takehomeassessment.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ReportServiceTest {
    @Mock
    private CourseService courseService;
    @Mock
    private UserService userService;
    @Mock
    private EnrollmentService enrollmentService;
    @Mock
    private Cache cache;
    @InjectMocks
    private ReportService reportService;
    @Test
    void shouldReturnCourseReportWithStudentDetails() {
        Integer courseId = 1;
        Integer userId1 = 101;
        Integer userId2 = 102;

        ExternalCourseDetail courseDetail = new ExternalCourseDetail();
        courseDetail.setName("Java Basics");
        courseDetail.setHeading("Learn Java Programming");

        ExternalCourseDetailResponse courseResponse = new ExternalCourseDetailResponse();
        courseResponse.setCourse(courseDetail);

        Enrollment enrollment1 = new Enrollment();
        enrollment1.setUserId(userId1);

        Enrollment enrollment2 = new Enrollment();
        enrollment2.setUserId(userId2);

        ExternalEnrollmentResponse enrollmentsResponse = new ExternalEnrollmentResponse();
        enrollmentsResponse.setEnrollments(List.of(enrollment1, enrollment2));

        ExternalUserDetail user1 = new ExternalUserDetail();
        user1.setName("John Doe");
        user1.setEmail("john@example.com");

        ExternalUserDetail user2 = new ExternalUserDetail();
        user2.setName("Jane Smith");
        user2.setEmail("jane@example.com");

        when(courseService.getCourseById(courseId)).thenReturn(courseResponse);
        when(enrollmentService.getEnrollmentsByCourseId(courseId)).thenReturn(enrollmentsResponse);
        when(cache.getStudent(userId1)).thenReturn(null);
        when(cache.getStudent(userId2)).thenReturn(null);
        when(userService.getUserById(userId1)).thenReturn(user1);
        when(userService.getUserById(userId2)).thenReturn(user2);

        CourseReport result = reportService.getReportOfStudentsEnrolledByCourseId(courseId);

        assertEquals("Java Basics", result.getName());
        assertEquals("Learn Java Programming", result.getHeading());
        assertEquals(2, result.getEnrolledStudents().size());
        assertEquals("John Doe", result.getEnrolledStudents().get(0).getName());
        assertEquals("john@example.com", result.getEnrolledStudents().get(0).getEmail());
        assertEquals("Jane Smith", result.getEnrolledStudents().get(1).getName());

        verify(courseService, times(1)).getCourseById(courseId);
        verify(enrollmentService, times(1)).getEnrollmentsByCourseId(courseId);
        verify(userService, times(2)).getUserById(any(Integer.class));
        verify(cache, times(2)).putStudent(any(Integer.class), any(Student.class));
        verify(cache, times(2)).updatePaginationDataTotalInCache();
    }

    @Test
    void shouldUseCachedStudentWhenAvailable() {
        Integer userId = 101;
        Student cachedStudent = new Student("Cached User", "cached@example.com");

        when(cache.getStudent(userId)).thenReturn(cachedStudent);

        Student result = reportService.getStudent(userId);

        assertEquals("Cached User", result.getName());
        assertEquals("cached@example.com", result.getEmail());

        verify(userService, never()).getUserById(userId);
        verify(cache, times(1)).getStudent(userId);
    }
}