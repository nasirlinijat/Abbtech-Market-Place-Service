package com.abbtech.service.impl;

import com.abbtech.dto.request.RequestNotificationDto;
import com.abbtech.dto.response.ResponseNotificationDto;
import com.abbtech.exception.ProductErrorEnum;
import com.abbtech.exception.ProductException;
import com.abbtech.model.Notification;
import com.abbtech.model.enums.NotificationStatus;
import com.abbtech.repository.NotificationRepository;
import com.abbtech.repository.secirity.UserRepository;
import com.abbtech.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public Page<ResponseNotificationDto> getAll(int pageNumber, int pageSize) {
        return notificationRepository.findAll(PageRequest.of(pageNumber, pageSize)).map(this::toResponseDto);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseNotificationDto getById(Long id) {
        return toResponseDto(findNotificationOrThrow(id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<ResponseNotificationDto> getByUserId(Long userId) {
        return notificationRepository.findByUser_Id(userId).stream().map(this::toResponseDto).toList();
    }

    @Override
    @Transactional
    public ResponseNotificationDto add(RequestNotificationDto request) {
        Notification notification = new Notification();
        notification.setUser(userRepository.findById(request.userId())
                .orElseThrow(() -> new ProductException(ProductErrorEnum.USER_NOT_FOUND)));
        notification.setMessage(request.message());
        notification.setStatus(request.status());
        return toResponseDto(notificationRepository.save(notification));
    }

    @Override
    @Transactional
    public ResponseNotificationDto markAsRead(Long id) {
        Notification notification = findNotificationOrThrow(id);
        notification.setStatus(NotificationStatus.READ);
        return toResponseDto(notificationRepository.save(notification));
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        notificationRepository.deleteById(id);
    }

    private Notification findNotificationOrThrow(Long id) {
        return notificationRepository.findById(id)
                .orElseThrow(() -> new ProductException(ProductErrorEnum.NOTIFICATION_NOT_FOUND));
    }

    private ResponseNotificationDto toResponseDto(Notification notification) {
        return new ResponseNotificationDto(
                notification.getId(),
                notification.getUser().getId(),
                notification.getTimestamp(),
                notification.getMessage(),
                notification.getStatus()
        );
    }
}
