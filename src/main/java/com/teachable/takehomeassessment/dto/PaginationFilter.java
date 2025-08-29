package com.teachable.takehomeassessment.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaginationFilter {
    private Integer page;
    private Integer per;
    @JsonProperty("search_after")
    private Integer searchAfter;
    private String email;
}