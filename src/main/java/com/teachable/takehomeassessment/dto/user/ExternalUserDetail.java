package com.teachable.takehomeassessment.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExternalUserDetail {
    private Integer id;
    private String name;
    private String email;
    @JsonProperty("last_sign_in_ip")
    private String lastSignInIp;
    private String role;
    private List<EnrolledCourse> courses;
    private List<Tag> tags;
}