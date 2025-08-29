package com.teachable.takehomeassessment.controller;

import com.teachable.takehomeassessment.Cache;
import com.teachable.takehomeassessment.domain.CourseReport;
import com.teachable.takehomeassessment.dto.PaginationFilter;
import com.teachable.takehomeassessment.dto.course.ExternalCourseDetailResponse;
import com.teachable.takehomeassessment.dto.course.ExternalCourseSummaryResponse;
import com.teachable.takehomeassessment.dto.enrollment.ExternalEnrollmentResponse;
import com.teachable.takehomeassessment.dto.user.ExternalUserDetail;
import com.teachable.takehomeassessment.dto.user.ExternalUserResponse;
import com.teachable.takehomeassessment.service.CourseService;
import com.teachable.takehomeassessment.service.EnrollmentService;
import com.teachable.takehomeassessment.service.ReportService;
import com.teachable.takehomeassessment.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reports")
@Tag(name = "Course reports", description = "Endpoints for generating course reports")
public class ReportController {
    @Autowired
    private CourseService courseService;
    @Autowired
    private UserService userService;
    @Autowired
    private EnrollmentService enrollmentService;
    @Autowired
    private ReportService reportService;
    @Autowired
    private Cache cache;

    @GetMapping("/courses")
    @Operation(
            summary = "Get all published courses",
            description = "Retrieves an object with a list of all published courses and pagination data from the Teachable API."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Successfully retrieved courses",
            content = @Content(mediaType = "application/json")
    )
    public ResponseEntity<ExternalCourseSummaryResponse> getAllPublishedCourses() {
        return ResponseEntity.ok(courseService.getAllPublishedCourses());
    }

    @GetMapping("/courses/{id}")
    @Operation(
            summary = "Get course details by course ID",
            description = "Retrieves an object containing an object of the course details from the Teachable API. Used primarily for testing."
    )
    @ApiResponses( value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully retrieved course",
                    content = @Content(mediaType = "application/json")
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Course not found"
            )
    })
    public ResponseEntity<ExternalCourseDetailResponse> getCourseById(@PathVariable Integer id) {
        return ResponseEntity.ok(courseService.getCourseById(id));
    }

    @GetMapping("/users/{id}")
    @Operation(
            summary = "Get user details by user ID",
            description = "Retrieves an object containing user details from the Teachable API. Used primarily for testing."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully retrieved an user",
                    content = @Content(mediaType = "application/json")
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "User not found"
            )
    })
    public ResponseEntity<ExternalUserDetail> getUserById(@PathVariable Integer id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @GetMapping("/users")
    @Operation(
            summary = "Get all users from the school and save in cache",
            description = "Retrieves an object with a list of all users from the school from the Teachable API, and save results in cache."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Successfully retrieved users",
            content = @Content(mediaType = "application/json")
    )
    public ResponseEntity<ExternalUserResponse> getAllUsers(@ModelAttribute PaginationFilter filter) {
        return ResponseEntity.ok(userService.getAllUsers(filter));
    }

    @GetMapping("/courses/{courseId}/enrollments")
    @Operation(
            summary = "Get all enrollments from a course",
            description = "Retrieves an object with a list of all enrollments and pagination data from the Teachable API. Used primarily for testing."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully retrieved enrollments",
                    content = @Content(mediaType = "application/json")
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Course not found"
            )
    })
    public ResponseEntity<ExternalEnrollmentResponse> getEnrollmentsByCourseId(@PathVariable Integer courseId) {
        return ResponseEntity.ok(enrollmentService.getEnrollmentsByCourseId(courseId));
    }

    @GetMapping("/course/{courseId}/enrolled-students")
    @Operation(
            summary = "Get course enrollment report",
            description = "Generates a report for a specific course including all enrolled students"
    )
    @ApiResponses(value = {
        @ApiResponse(
                responseCode = "200",
                description = "Successfully generated course report",
                content = @Content(mediaType = "application/json")
        ),
        @ApiResponse(
                responseCode = "404",
                description = "Course not found",
                content = @Content(mediaType = "application/json")
        )
    })
    public ResponseEntity<CourseReport> getReportOfStudentsEnrolledByCourseId(@PathVariable Integer courseId) {
        return ResponseEntity.ok(reportService.getReportOfStudentsEnrolledByCourseId(courseId));
    }

    @GetMapping("/cache")
    @Operation(
            summary = "Get cached students",
            description = "Retrieves an object of cached students with metadata about quantity in cache and total students in database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Successfully retrieved cached students",
            content = @Content(mediaType = "application/json")
    )
    public ResponseEntity<Cache> getCache() {
        return ResponseEntity.ok(cache);
    }
}