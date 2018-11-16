package com.fangdd.tp.service;

import com.fangdd.tp.dto.request.ApiRequestSave;
import com.fangdd.tp.entity.ApiRequest;
import com.fangdd.tp.entity.User;

import java.util.List;

/**
 * @auth ycoe
 * @date 18/12/4
 */
public interface ApiRequestService {
    ApiRequest save(User user, ApiRequestSave request);

    void delete(User user, String apiRequestId);

    List<ApiRequest> query(User user, String apiKey);
}
