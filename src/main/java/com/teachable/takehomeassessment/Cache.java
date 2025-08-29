package com.teachable.takehomeassessment;

import com.teachable.takehomeassessment.domain.Student;
import com.teachable.takehomeassessment.dto.Meta;
import com.teachable.takehomeassessment.dto.PaginationData;
import com.teachable.takehomeassessment.dto.user.ExternalUserSummary;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class Cache {
    @Getter
    private Map<Integer, Student> cachedStudents = new ConcurrentHashMap<>();

    @Getter
    private PaginationData paginationData = new PaginationData();

    public void putStudents(List<ExternalUserSummary> students) {
        students.forEach(student -> {
            cachedStudents.put(student.getId(), new Student(student.getName(), student.getEmail()));
        });
    }

    public void setPaginationDataTotal(Meta paginationData) {
        this.paginationData.setTotal(paginationData.getTotal());
    }

    public void updatePaginationDataTotalInCache() {
        this.paginationData.setTotalInCache(this.cachedStudents.size());
    }

    public Student getStudent(Integer userId) {
        return this.cachedStudents.get(userId);
    }

    public void putStudent(Integer userId, Student cachedStudent) {
        this.cachedStudents.put(userId, cachedStudent);
    }
}