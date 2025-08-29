package com.teachable.takehomeassessment.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaginationData {
    private Integer total;
    @JsonProperty("total_in_cache")
    private Integer totalInCache;
}