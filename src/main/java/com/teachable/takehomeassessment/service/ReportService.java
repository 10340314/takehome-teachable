package com.teachable.takehomeassessment.service;

import com.teachable.takehomeassessment.Cache;
import com.teachable.takehomeassessment.domain.CourseReport;
import com.teachable.takehomeassessment.domain.Student;
import com.teachable.takehomeassessment.dto.course.ExternalCourseDetail;
import com.teachable.takehomeassessment.dto.enrollment.Enrollment;
import com.teachable.takehomeassessment.dto.user.ExternalUserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportService {
    @Autowired
    private CourseService courseService;
    @Autowired
    private UserService userService;
    @Autowired
    private EnrollmentService enrollmentService;
    @Autowired
    private Cache cache;

    public ExternalCourseDetail getCourse(Integer courseId) {
        return courseService.getCourseById(courseId).getCourse();
    }

    private List<Enrollment> getEnrollments(Integer courseId) {
        return enrollmentService.getEnrollmentsByCourseId(courseId).getEnrollments();
    }

    public CourseReport getReportOfStudentsEnrolledByCourseId(Integer courseId) {
        ExternalCourseDetail externalCourseDetail = getCourse(courseId);
        List<Enrollment> externalEnrollment = getEnrollments(courseId);
        List<Student> enrolledStudents = getEnrolledStudents(externalEnrollment);

        return new CourseReport(externalCourseDetail.getName(), externalCourseDetail.getHeading(), enrolledStudents);
    }
    public List<Student> getEnrolledStudents(List<Enrollment> enrollments) {
        return enrollments.stream().map(enrollment -> getStudent(enrollment.getUserId())).toList();
    }

    public Student getStudent(Integer userId) {
        Student cachedStudent = getCachedStudent(userId);
        if (cachedStudent == null) {
            ExternalUserDetail externalUser = userService.getUserById(userId);
            cachedStudent = new Student(externalUser.getName(), externalUser.getEmail());
            cache.putStudent(userId, cachedStudent);
        }
        cache.updatePaginationDataTotalInCache();
        return cachedStudent;
    }

    public Student getCachedStudent(Integer userId) {
        return cache.getStudent(userId);
    }

    public Student getStudentDetails(Integer userId) {
        ExternalUserDetail user = userService.getUserById(userId);
        return new Student(user.getName(), user.getEmail());
    }
}