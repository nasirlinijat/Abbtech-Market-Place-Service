package com.abbtech.controller;

import com.abbtech.dto.request.RequestNotificationDto;
import com.abbtech.dto.response.ResponseNotificationDto;
import com.abbtech.service.NotificationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Page<ResponseNotificationDto> getAll(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize) {
        return notificationService.getAll(pageNumber, pageSize);
    }

    @GetMapping("/{id}")
    public ResponseNotificationDto getById(@PathVariable Long id) {
        return notificationService.getById(id);
    }

    @GetMapping("/user/{userId}")
    public List<ResponseNotificationDto> getByUserId(@PathVariable Long userId) {
        return notificationService.getByUserId(userId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseNotificationDto add(@RequestBody @Valid RequestNotificationDto request) {
        return notificationService.add(request);
    }

    @PatchMapping("/{id}/read")
    public ResponseNotificationDto markAsRead(@PathVariable Long id) {
        return notificationService.markAsRead(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteById(@PathVariable Long id) {
        notificationService.deleteById(id);
    }
}
