package com.teachable.takehomeassessment.dto.user;

import com.teachable.takehomeassessment.dto.Meta;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExternalUserResponse {
    private List<ExternalUserSummary> users;
    private Meta meta;
}