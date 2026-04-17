package com.abbtech.controller;

import com.abbtech.dto.request.RequestItemDto;
import com.abbtech.dto.response.ResponseItemDto;
import com.abbtech.service.abstracts.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/items")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping()
    public List<ResponseItemDto> getAll() {
        return itemService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseItemDto getById(@PathVariable Long id) {
        return itemService.getById(id);
    }

    @PostMapping
    public ResponseItemDto create(@RequestBody RequestItemDto request) {
        return itemService.create(request);
    }

    @PostMapping("/bulk")
    public List<ResponseItemDto> bulkCreate(@RequestBody List<RequestItemDto> requests) {
        return itemService.bulkCreate(requests);
    }

    @PutMapping("/{id}")
    public ResponseItemDto update(@PathVariable Long id, @RequestBody RequestItemDto request) {
        return itemService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        itemService.delete(id);
    }

}
