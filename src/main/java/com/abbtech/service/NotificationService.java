package com.abbtech.service;

import com.abbtech.dto.request.RequestNotificationDto;
import com.abbtech.dto.response.ResponseNotificationDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface NotificationService {

    Page<ResponseNotificationDto> getAll(int pageNumber, int pageSize);

    ResponseNotificationDto getById(Long id);

    List<ResponseNotificationDto> getByUserId(Long userId);

    ResponseNotificationDto add(RequestNotificationDto request);

    ResponseNotificationDto markAsRead(Long id);

    void deleteById(Long id);
}
