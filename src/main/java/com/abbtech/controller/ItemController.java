package com.abbtech.controller;

import com.abbtech.dto.request.RequestItemDto;
import com.abbtech.dto.response.ResponseItemDto;
import com.abbtech.model.enums.SortDirectionEnum;
import com.abbtech.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/items")
public class ItemController {

    private final ItemService itemService;

    @GetMapping
    public Page<ResponseItemDto> getAll(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "ASC") String sortDirection,
            @RequestParam(defaultValue = "name") String sortField) {
        return itemService.getAll(pageNumber, pageSize, SortDirectionEnum.valueOf(sortDirection), sortField);
    }

    @GetMapping("/{id}")
    public ResponseItemDto getById(@PathVariable Long id) {
        return itemService.getById(id);
    }

    @GetMapping("/filter")
    public Page<ResponseItemDto> getPriceRange(
            @RequestParam double min,
            @RequestParam double max,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize) {
        return itemService.getPriceRange(min, max, pageNumber, pageSize);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseItemDto add(@RequestBody RequestItemDto request) {
        return itemService.add(request);
    }

    @PostMapping("/all")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveAll(@RequestBody List<RequestItemDto> request) {
        itemService.saveAll(request);
    }

    @PutMapping("/{name}")
    public ResponseItemDto updateByName(@PathVariable String name, @RequestBody RequestItemDto request) {
        return itemService.updateByName(name, request);
    }

    @PatchMapping("/{name}")
    public ResponseItemDto partialUpdateByName(@PathVariable String name, @RequestParam String itemDescription) {
        return itemService.partialUpdateByName(name, itemDescription);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        itemService.deleteById(id);
    }
}
