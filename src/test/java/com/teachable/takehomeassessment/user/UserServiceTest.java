package com.teachable.takehomeassessment.user;

import com.teachable.takehomeassessment.client.UserClient;
import com.teachable.takehomeassessment.dto.user.EnrolledCourse;
import com.teachable.takehomeassessment.dto.user.ExternalUserDetail;
import com.teachable.takehomeassessment.dto.user.Tag;
import com.teachable.takehomeassessment.service.UserService;
import feign.FeignException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserClient userClient;

    @InjectMocks
    private UserService userService;

    @Test
    void shouldReturnUserWithDetails() {
        Integer userId = 1;

        List<EnrolledCourse> enrolledCourses = List.of(
                new EnrolledCourse(1, "Course test", true, OffsetDateTime.now(), null, 0.0)
        );
        List<Tag> userTags = List.of(
                new Tag("Tag test")
        );
        ExternalUserDetail userDetailResponse = new ExternalUserDetail(1, "User test", "email@test.com", "216.158.137.35", "owner", enrolledCourses, userTags);

        when(userClient.getUserById(userId)).thenReturn(userDetailResponse);

        ExternalUserDetail result = userService.getUserById(userId);

        assertEquals(userDetailResponse, result);
        assertEquals("User test", result.getName());
        assertEquals("email@test.com", result.getEmail());
    }

    @Test
    void shouldThrowNotFoundExceptionWhenUserDoesNotExist() {
        Integer invalidUserId = 999;

        FeignException.NotFound notFoundException = Mockito.mock(FeignException.NotFound.class);
        when(userClient.getUserById(invalidUserId)).thenThrow(notFoundException);

        assertThrows(FeignException.NotFound.class,
                () -> userService.getUserById(invalidUserId));

        verify(userClient, times(1)).getUserById(invalidUserId);
    }
}