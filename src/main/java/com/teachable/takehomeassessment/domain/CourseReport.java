package com.teachable.takehomeassessment.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseReport {
    private String name;
    private String heading;
    private List<Student> enrolledStudents;
}