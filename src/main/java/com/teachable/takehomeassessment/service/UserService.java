package com.teachable.takehomeassessment.service;

import com.teachable.takehomeassessment.Cache;
import com.teachable.takehomeassessment.client.UserClient;
import com.teachable.takehomeassessment.dto.PaginationFilter;
import com.teachable.takehomeassessment.dto.user.ExternalUserDetail;
import com.teachable.takehomeassessment.dto.user.ExternalUserResponse;
import com.teachable.takehomeassessment.dto.user.ExternalUserSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {
    @Autowired
    private UserClient userClient;
    @Autowired
    private Cache cache;

    public ExternalUserDetail getUserById(Integer id) {
        return userClient.getUserById(id);
    }

    public ExternalUserResponse getAllUsers(PaginationFilter filter) {
        Map<String, Object> params = toMap(filter);
        ExternalUserResponse response = userClient.getAllUsers(params);
        List<ExternalUserSummary> usersSummary = response.getUsers();
        cache.putStudents(usersSummary);
        cache.setPaginationDataTotal(response.getMeta());
        cache.updatePaginationDataTotalInCache();
        return response;
    }

    private Map<String, Object> toMap(PaginationFilter filter) {
        Map<String, Object> params = new HashMap<>();
        if (filter.getPage() != null) params.put("page", filter.getPage());
        if (filter.getPer() != null) params.put("per", filter.getPer());
        if (filter.getEmail() != null) params.put("email", filter.getEmail());
        if (filter.getSearchAfter() != null) params.put("search_after",filter.getSearchAfter());

        return params;
    }
}
