package com.teachable.takehomeassessment.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Meta {
    private Integer total;
    private Integer page;
    private Integer from;
    private Integer to;
    @JsonProperty("per_page")
    private Integer perPage;
    @JsonProperty("number_of_pages")
    private Integer numberOfPages;
}